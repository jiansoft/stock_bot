package org.zapto.jiansoft.bot.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 組態服務類，用於載入和管理應用組態。
 */
@Service
public class ConfigurationService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    // 組態檔案路徑
    @Value("${config.path:config.json}")
    private String configPath;

    // Jackson的對象對應器，用於將JSON轉換成Java對象
    private final ObjectMapper mapper;

    private final Dotenv env;

    // 快取的組態對象
    private Config cachedConfig;

    /**
     * 建構函式，自動注入所需的依賴。
     *
     * @param mapper ObjectMapper對象
     */
    @Autowired
    public ConfigurationService(ObjectMapper mapper, Dotenv env) {
        this.mapper = mapper;
        this.env = env;
    }

    /**
     * 初始化方法，服務啟動時自動執行，載入組態檔案。
     */
    @   PostConstruct
    public void init() {
        try {
            cachedConfig = loadConfig(configPath);
        } catch (IOException e) {
            logger.error("Failed to load configuration during initialization", e);
            throw new IllegalStateException("Configuration must be available at application start", e);
        }
    }

    /**
     * 獲取已載入的組態。
     *
     * @return 返回快取的組態對象。
     */
    public Config loadConfiguration() {
        return cachedConfig;
    }

    /**
     * 根據指定路徑載入組態檔案。
     *
     * @param path 組態檔案的路徑
     * @return 返回組態對象
     * @throws IOException 如果讀取組態檔案失敗
     */
    private Config loadConfig(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        if (!resource.exists()) {
            throw new FileNotFoundException("Resource not found at path: " + path);
        }

        try (InputStream is = resource.getInputStream()) {
            var config = mapper.readValue(is, Config.class);
            overrideWithEnv(config);
            return config;
        }
    }

    /**
     * 將來至於 env 的設定值覆蓋掉 json 上的設定值
     */
    private void overrideWithEnv(Config config) {
        String telegramToken = env.get("TELEGRAM_TOKEN");
        if (telegramToken != null && !telegramToken.isBlank()) {
            config.getBot().getTelegram().setToken(telegramToken);
        }

        String telegramAllowed = env.get("TELEGRAM_ALLOWED");
        if (telegramAllowed != null && !telegramAllowed.isBlank()) {
            try {
                Map<Long, String> map = mapper.readValue(telegramAllowed, new TypeReference<>() {
                });

                config.getBot().getTelegram().setAllowed(map);
            } catch (Exception e) {
                logger.error("Failed to mapper.readValue", e);
            }
        }

        String goGrpcTarget = env.get("GO_GRPC_TARGET");
        if (goGrpcTarget != null && !goGrpcTarget.isBlank()) {
            config.getRpc().getGoService().setTarget(goGrpcTarget);
        }

        String rustGrpcTarget = env.get("RUST_GRPC_TARGET");
        if (rustGrpcTarget != null && !rustGrpcTarget.isBlank()) {
            config.getRpc().getRustService().setTarget(rustGrpcTarget);
        }
    }
}
package org.zapto.jiansoft.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
public class EnvConfig {

    @Bean
    public Dotenv dotEnv() {
        return Dotenv.load();
    }
}

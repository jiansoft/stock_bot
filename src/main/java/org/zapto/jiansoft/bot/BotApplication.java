package org.zapto.jiansoft.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.zapto.jiansoft.bot.config.ConfigurationService;
import org.zapto.jiansoft.bot.telegram.StockBot;

@EnableScheduling
@SpringBootApplication
public class BotApplication {

    private static final Logger logger = LoggerFactory.getLogger(BotApplication.class);

    private final ConfigurationService configurationService;

    @Autowired
    public BotApplication(ConfigurationService configurationService) {
        this.configurationService = configurationService;
        //this.telegramBotService = telegramBotService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {


        try {
            var config = configurationService.loadConfiguration();
            System.out.println("Telegram:"+config.getBot().getTelegram());
            logger.info("GoService:" +config.getRpc().getGoService());

            String botToken = config.getBot().getTelegram().getToken();
            StockBot bot = new StockBot(botToken);

            var app = new TelegramBotsLongPollingApplication();
            app.registerBot(botToken, bot);

        } catch (TelegramApiException e) {
            logger.error("An error occurred initializing the Telegram bot: ", e);
            throw new IllegalStateException("Failed to initialize the Telegram bot", e);
        }

    }
}

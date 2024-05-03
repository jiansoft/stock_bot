package org.zapto.jiansoft.bot.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 聊天機器人設定
 */

@NoArgsConstructor
@Setter
@Getter
public class Bot {

    private Telegram telegram;

    public Bot(Telegram telegram) {
        this.telegram = telegram;
    }
}

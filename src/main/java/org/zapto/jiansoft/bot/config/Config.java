package org.zapto.jiansoft.bot.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Config {

    /**
     * 系統設定
     */
    private System system;

    /**
     * 聊天機器人設定
     */
    private Bot bot;

    /**
     * 遠端調用
     */
    private Rpc rpc;

    private String host;

    private int port;
    private boolean useSSL;

}

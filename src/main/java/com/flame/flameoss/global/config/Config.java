package com.flame.flameoss.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:config.properties")
//@ConfigurationProperties(prefix = "config")
public class Config {

    @Value("${config.gameServerCheckKey}")
    private String gameServerCheckKey;

    @Value("${config.zkConnectString}")
    private String zkConnectString;

    public String getGameServerCheckKey() {
        return gameServerCheckKey;
    }

    public String getZkConnectString() {
        return zkConnectString;
    }

}

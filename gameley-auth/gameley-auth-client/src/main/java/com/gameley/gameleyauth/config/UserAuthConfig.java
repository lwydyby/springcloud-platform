package com.gameley.gameleyauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Created by wwmxd on 2018/01/04.
 */
@ConfigurationProperties(prefix = "auth.user")
public class UserAuthConfig {


    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

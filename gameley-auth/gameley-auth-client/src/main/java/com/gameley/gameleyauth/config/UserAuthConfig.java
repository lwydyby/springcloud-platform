package com.gameley.gameleyauth.config;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ace on 2017/9/15.
 */
public class UserAuthConfig {

    @Value("${auth.user.secret}")
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

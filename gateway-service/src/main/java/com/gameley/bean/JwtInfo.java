package com.gameley.bean;

import lombok.Data;

@Data
public class JwtInfo {
    private String token;
    private String clientId;
    private String name;
    private int expiresSecond;


}

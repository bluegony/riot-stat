package com.web.bmservice;

import lombok.Getter;

@Getter
public enum UserConfig {

    TEST1("test1", "","", "testnet.bitmex.com"),
    PROD1("prod1", "","", "www.bitmex.com"),
    PROD2("prod2", "","", "www.bitmex.com")
    ;


    private String code;
    private String domain;
    private String key;
    private String secret;

    UserConfig(String code, String key, String secret, String domain) {
        this.code = code;
        this.key = key;
        this.secret = secret;
        this.domain = domain;
    }
}

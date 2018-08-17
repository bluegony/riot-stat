package com.study.bmservice;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class UserService {

    private static UserService instance = new UserService();

    private UserService () {
        System.out.println( "call UserService constructor." );
    }
    // 조회 method
    public static UserService getInstance () {
        return instance;
    }


    private static int initCount = 0;
    private UserConfig user;

    public void setUser(UserConfig user) {
        this.user = user;
        initCount++;
        if(initCount>1) {
            log.info("init count={}", initCount);
            throw new RuntimeException("Init User Error!!!!!");
        }
    }
}

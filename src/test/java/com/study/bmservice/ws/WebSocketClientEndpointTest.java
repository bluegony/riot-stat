package com.study.bmservice.ws;

import com.study.config.AppConfig;
import com.study.config.DatabaseConfig;
import com.study.config.JsonConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 2018. 8. 13..
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfig.class, DatabaseConfig.class, JsonConfig.class})
@ContextConfiguration(classes={BmWebSocket.class})
public class WebSocketClientEndpointTest {

    @Autowired
    BmWebSocket bmWebSocket;

    @Test
    public void wstest() {
        bmWebSocket.connect();
    }
}
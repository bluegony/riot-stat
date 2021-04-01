package com.web.bmservice.ws;

import com.web.config.AppConfig;
import com.web.config.DatabaseConfig;
import com.web.config.JsonConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created on 2018. 8. 13..
 */
@Slf4j
//@RunWith(SpringRunner.class)
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
package com.study.bmservice.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class BmWsMessageHandler implements WebSocketClientEndpoint.MessageHandler {

    @Override
    public void handleMessage(String message) {

        try {
            HashMap<String, Object> result =  new ObjectMapper().readValue(message, HashMap.class);
            log.info("result = {}", result.get("subscribe"));
            if (message.contains("subscribe"))
                log.info(message);
            if(result.get("subscribe")!=null)
                log.info("{}",result.get("subscribe"));
        } catch (Exception e) {
            log.info("exception", e);
        }
    }
}

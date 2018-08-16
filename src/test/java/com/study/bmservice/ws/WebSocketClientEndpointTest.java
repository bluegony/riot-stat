package com.study.bmservice.ws;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by 1000773 on 2018. 8. 13..
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class WebSocketClientEndpointTest {


    @Value("${ws.domain}") private String domain;

    @Test
    public void wstest() {

        try {
            String url = "wss://" + domain;
//            String path = "/realtime?subscribe=quote:XBTUSD,trade:XBTUSD,order:XBTUSD,execution:XBTUSD,margin,position";
            String path = "/realtime?subscribe=quote:XBTUSD,trade:XBTUSD,instrument:XBTUSD" +   // public
                    ",order:XBTUSD,execution:XBTUSD,margin,position";                           // auth
            log.info("connecting {}",url+path);
            // open websocket
            final WebSocketClientEndpoint clientEndPoint = new WebSocketClientEndpoint(
                    new URI(url+path) );

            // add listener
            clientEndPoint.addMessageHandler(new WebSocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

            // wait 5 seconds for messages from websocket
//            Thread.sleep(15000);
            while(true) {
                Thread.sleep(1000);
            }

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
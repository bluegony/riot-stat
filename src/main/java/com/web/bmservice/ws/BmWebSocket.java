package com.web.bmservice.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.bmservice.UserConfig;
import com.web.bmservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created on 2018. 8. 16..
 */
@Slf4j
@Service
public class BmWebSocket {

    @Autowired
    PriceMapper priceMapper;
    @Autowired
    ObjectMapper objectMapper;

    public void connect() {

        UserService userService = UserService.getInstance();
        userService.setUser(UserConfig.PROD1);
        UserConfig user = userService.getUser();
        while(true) {
            try {
                connectBlock(user);
            } catch (Exception e) {
                log.info("Exception", e);
            }
            try {
                Thread.sleep(30000);
            } catch(Exception e) {
                log.info("Exception", e);
            }
        }

    }

    public void connectBlock(UserConfig user) {

        try {
            String url = "wss://" + user.getDomain();
//            String path = "/realtime?subscribe=quote:XBTUSD,trade:XBTUSD,order:XBTUSD,execution:XBTUSD,margin,position";
            String path = "/realtime?subscribe=quote:XBTUSD,trade:XBTUSD,instrument:XBTUSD" +   // public
                    ",order:XBTUSD,execution:XBTUSD,margin,position";                           // auth
            log.info("connecting {}",url+path);
            // open websocket
            final WebSocketClientEndpoint clientEndPoint = new WebSocketClientEndpoint( new URI(url+path) );

            // add listener
            clientEndPoint.addMessageHandler(new BmMessageHandler(priceMapper, objectMapper));

            // send message to websocket
            clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

            // wait 5 seconds for messages from websocket
//            Thread.sleep(15000);
            while(true) {
                Thread.sleep(1000);
                if(clientEndPoint.getConnectionCount()==0) {
                    log.info("connection closed");
                    break;
                }
            }

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}

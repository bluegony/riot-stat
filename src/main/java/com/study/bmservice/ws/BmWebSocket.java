package com.study.bmservice.ws;

import com.study.bmservice.UserConfig;
import com.study.bmservice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by 1000773 on 2018. 8. 16..
 */
@Slf4j
@Service
public class BmWebSocket {

    @Autowired
    PriceMapper priceMapper;

    public void connect() {

        while(true) {
            try {
                connectBlock();
            } catch (Exception e) {
                log.info("Exception", e);
            }
        }

    }

    public void connectBlock() {

        UserService userService = UserService.getInstance();
        userService.setUser(UserConfig.PROD1);
        UserConfig user = userService.getUser();

        try {
            String url = "wss://" + user.getDomain();
//            String path = "/realtime?subscribe=quote:XBTUSD,trade:XBTUSD,order:XBTUSD,execution:XBTUSD,margin,position";
            String path = "/realtime?subscribe=quote:XBTUSD,trade:XBTUSD,instrument:XBTUSD" +   // public
                    ",order:XBTUSD,execution:XBTUSD,margin,position";                           // auth
            log.info("connecting {}",url+path);
            // open websocket
            final WebSocketClientEndpoint clientEndPoint = new WebSocketClientEndpoint( new URI(url+path) );

            // add listener
            clientEndPoint.addMessageHandler(new BmWsMessageHandler(priceMapper));

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

package com.study.bmservice.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.bmservice.UserConfig;
import com.study.bmservice.UserService;
import com.study.bmservice.dto.Instrument;
import com.study.bmservice.dto.Price;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class BmMessageHandler implements WebSocketClientEndpoint.MessageHandler {

    private PriceMapper priceMapper;
    private static Price price = new Price();

    public BmMessageHandler(PriceMapper priceMapper) {
        log.info("creating handler : {}", priceMapper);
        this.priceMapper = priceMapper;
    }

    @Override
    public void handleMessage(String message) {

        UserConfig user = UserService.getInstance().getUser();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HashMap<String, Object> result = objectMapper.readValue(message, HashMap.class);

            if(result.get("subscribe")!=null && result.get("success")!=null) {
                log.info("Subscribed to {}", result.get("subscribe"));
            } else if(result.get("action")!=null) {
                String table = (String)result.get("table");
                if(table.equals("instrument")) {
                    Instrument instrumentList = objectMapper.readValue(message, Instrument.class);
                    Instrument instrument = instrumentList.getData().get(0);
                    if(result.get("action").equals("partial") || result.get("action").equals("update")) {
                        if(instrument.hasLastPrice()) {
                            log.debug(instrument.toString());
                            instrument.setUserCode(user.getCode());
                            priceMapper.insertPriceTicker(instrument);
                            price.setData(instrument);
                            log.debug(price.toString());
                            priceMapper.insertPrice(price);

                        } else {
                            log.debug("instrument data without price : {}",message);
                        }
                    } else if(result.get("action").equals("insert")) {
                        log.info(message);
                        throw new RuntimeException("insert instrument!!!!!!");
                    }
                } else if(table.equals("quote")) {
                    // do nothing
                } else if(table.equals("trade")) {
                    // do nothing
                } else {
                        log.info(message);
                }
            } else {
                log.debug("other message : {}",message);
            }
        } catch (Exception e) {
            log.info("exception", e);
        }
    }
}

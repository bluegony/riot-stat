package com.study.bmservice.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.bmservice.UserConfig;
import com.study.bmservice.UserService;
import com.study.bmservice.dto.Instrument;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class BmWsMessageHandler implements WebSocketClientEndpoint.MessageHandler {


    private PriceMapper priceMapper;

    public BmWsMessageHandler(PriceMapper priceMapper) {
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
                        if(instrument.isValidPriceData()) {
                            log.info(instrument.toString());
                            instrument.setUserCode(user.getCode());
                            priceMapper.insertPriceTicker(instrument);
                        } else {
                            log.debug("instrument data without price : {}",message);
                        }
                    } else if(result.get("action").equals("insert")) {
                        log.info(message);
                        throw new RuntimeException("insert instrument!!!!!!");
                    }
                } else {
                    log.debug("other table data : {}",message);
                }
            } else {
                log.debug("not treated message : {}",message);
            }
        } catch (Exception e) {
            log.info("exception", e);
        }
    }
}

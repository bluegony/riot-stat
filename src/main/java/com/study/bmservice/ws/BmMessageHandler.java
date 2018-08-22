package com.study.bmservice.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.bmservice.UserConfig;
import com.study.bmservice.UserService;
import com.study.bmservice.dto.*;
import com.study.utils.StringTools;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class BmMessageHandler implements WebSocketClientEndpoint.MessageHandler {

    private PriceMapper priceMapper;
    private ObjectMapper objectMapper;
    private static Price price = new Price();
    private static Margin margin = new Margin();
    private static Position position = new Position();
    private static Order orderList = new Order();
    private static int trueRangeMinute = 0;

    public BmMessageHandler(PriceMapper priceMapper, ObjectMapper objectMapper) {
        log.info("creating handler : {}", priceMapper);
        this.priceMapper = priceMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleMessage(String message) {

        UserConfig user = UserService.getInstance().getUser();
//        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

        try {
            HashMap<String, Object> result = objectMapper.readValue(message, HashMap.class);

            if(result.get("subscribe")!=null && result.get("success")!=null) {
                log.info("Subscribed to {}", result.get("subscribe"));
            } else if(result.get("action")!=null) {
                String table = (String)result.get("table");
                if(table.equals("instrument")) {
                    handleInstrumentAction(message, user, objectMapper, result);
                } else if(table.equals("margin")) {
                    handleMarginAction(message, objectMapper, result);
                } else if(table.equals("position")) {
                    handlePositionAction(message, objectMapper, result);
                } else if(table.equals("order")) {
                    handleOrderAction(message, objectMapper, result);
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

    private void handleInstrumentAction(String message, UserConfig user, ObjectMapper objectMapper, HashMap<String, Object> result) throws java.io.IOException {
        Instrument instrument = objectMapper.readValue(message, Instrument.class).getData().get(0);
        if(result.get("action").equals("partial") || result.get("action").equals("update")) {
            if(instrument.hasLastPrice()) {
                log.debug(instrument.toString());
                String userCode = user.getCode()+"_"+user.getKey().substring(0,2);
                instrument.setUserCode(userCode);
                priceMapper.insertPriceTicker(instrument);
                price.setData(instrument);
                log.debug(price.toString());
                priceMapper.insertPrice(price);

//                if(instrument.getTimestamp().getMinute() != trueRangeMinute) {
//                    log.info("{} {}", instrument.getTimestamp().getMinute(), trueRangeMinute);
//                    TrueRange trueRange = priceMapper.selectHighLowPrice();
//                    trueRange.setLastPrice(priceMapper.selectLastPrice());
//                    log.info(StringTools.objectToJson(trueRange));
//                    trueRangeMinute = instrument.getTimestamp().getMinute();
//                }
            } else {
                log.debug("instrument data without price : {}",message);
            }
        } else if(result.get("action").equals("insert")) {
            log.info(message);
            throw new RuntimeException("insert instrument!!!!!!");
        }
    }

    private void handleMarginAction(String message, ObjectMapper objectMapper, HashMap<String, Object> result) throws java.io.IOException {
        Margin newMargin = objectMapper.readValue(message, Margin.class).getData().get(0);
        if(result.get("action").equals("partial") || result.get("action").equals("update")) {
            log.debug(margin.toString());
            log.debug(newMargin.toString());
            if(margin.getAccount()==null) {
                margin = newMargin;
            } else {
                margin.update(newMargin);
                log.info(margin.toString());
            }
        } else if(result.get("action").equals("insert")) {
            log.info(message);
            throw new RuntimeException("insert margin!!!!!!");
        }
    }

    private void handlePositionAction(String message, ObjectMapper objectMapper, HashMap<String, Object> result) throws java.io.IOException {
        log.debug(message);
        Position newPosition = objectMapper.readValue(message, Position.class).getData().get(0);
        if(result.get("action").equals("partial") || result.get("action").equals("update")) {
            log.debug(position.toString());
            log.debug(newPosition.toString());
            if(position.getAccount()==null) {
                position = newPosition;
            } else {
                position.update(newPosition);
                log.info(position.toString());
            }

        } else if(result.get("action").equals("insert")) {
            log.info(message);
            throw new RuntimeException("insert position!!!!!!");
        }
    }


    private void handleOrderAction(String message, ObjectMapper objectMapper, HashMap<String, Object> result) throws java.io.IOException {
        Order newOrderList = objectMapper.readValue(message, Order.class);

        if(result.get("action").equals("partial")) {
            log.info("order:\n{}",message);
            orderList = newOrderList;
            printOrder(orderList);
        }
        else if(result.get("action").equals("update")) {
            log.info("order update:\n{}",message);
//                        log.debug("{}\n {}",orderList, newOrderList);
            for(Order no:newOrderList.getData()) {
                for(Order oo:orderList.getData()) {
                    if(no.getOrderID().equals(oo.getOrderID())) {
                        oo.update(no);
                    }
                }
            }
            printOrder(orderList);

        } else if(result.get("action").equals("insert")) {
            log.info("insert order!!!!!!\n{}",message);
            orderList.getData().addAll(newOrderList.getData());
            printOrder(orderList);
        }
    }

    private void printOrder(Order order) {
        log.info(StringTools.objectToJson(order));
    }
}

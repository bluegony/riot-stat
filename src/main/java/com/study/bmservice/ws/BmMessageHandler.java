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
    private static Price price = new Price();
    private static Margin margin = new Margin();
    private static Position position = new Position();
    private static Order orderList = new Order();

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

                        } else {
                            log.debug("instrument data without price : {}",message);
                        }
                    } else if(result.get("action").equals("insert")) {
                        log.info(message);
                        throw new RuntimeException("insert instrument!!!!!!");
                    }

                } else if(table.equals("margin")) {
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

                } else if(table.equals("position")) {
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

                } else if(table.equals("order")) {

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
    private void printOrder(Order order) {
        log.info(StringTools.objectToPrettyJson(order));
    }
}

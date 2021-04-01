package com.web.bmservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Slf4j
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private String orderID;
    private Long account;
    private String symbol;

    private String clOrdID;
    private Boolean workingIndicator;
    private String ordStatus;
    private Float simpleLeavesQty;
    private Long leavesQty;
    private Date timestamp;
    private String side;
    private Long orderQty;
    private Long price;

    private String ordType;
    private String timeInForce;
    private String text;

    private List<Order> data;

    public void update(Order no) {
        if(!account.equals(no.account) || !orderID.equals(no.orderID) || !symbol.equals(no.symbol)) {
            log.info("oo : {}\n no : {}", this, no);
            throw new RuntimeException("order is different");
        }
        clOrdID = (String)checkUpdate(clOrdID, no.clOrdID);
        ordStatus = (String)checkUpdate(ordStatus, no.ordStatus);
        side = (String)checkUpdate(side, no.side);
        ordType = (String)checkUpdate(ordType, no.ordType);
        timeInForce = (String)checkUpdate(timeInForce, no.timeInForce);
        text = (String)checkUpdate(text, no.text);
        orderQty = (Long)checkUpdate(orderQty, no.orderQty);
        leavesQty = (Long)checkUpdate(leavesQty, no.leavesQty);
        price = (Long)checkUpdate(price, no.price);
        workingIndicator = (Boolean)checkUpdate(workingIndicator, no.workingIndicator);
        simpleLeavesQty = (Float)checkUpdate(simpleLeavesQty, no.simpleLeavesQty);
        timestamp = no.timestamp;
    }
    private Object checkUpdate(Object older, Object newer) {
        if(newer!=null)
            return newer;
        else return older;
    }


//    "orderID": "guid",
//            "clOrdID": "symbol",
//            "clOrdLinkID": "symbol",
//            "account": "long",
//            "symbol": "symbol",
//            "side": "symbol",
//            "simpleOrderQty": "float",
//            "orderQty": "long",
//            "price": "float",
//            "displayQty": "long",
//            "stopPx": "float",
//            "pegOffsetValue": "float",
//            "pegPriceType": "symbol",
//            "currency": "symbol",
//            "settlCurrency": "symbol",
//            "ordType": "symbol",
//            "timeInForce": "symbol",
//            "execInst": "symbol",
//            "contingencyType": "symbol",
//            "exDestination": "symbol",
//            "ordStatus": "symbol",
//            "triggered": "symbol",
//            "workingIndicator": "boolean",
//            "ordRejReason": "symbol",
//            "simpleLeavesQty": "float",
//            "leavesQty": "long",
//            "simpleCumQty": "float",
//            "cumQty": "long",
//            "avgPx": "float",
//            "multiLegReportingType": "symbol",
//            "text": "symbol",
//            "transactTime": "timestamp",
//            "timestamp": "timestamp"
}

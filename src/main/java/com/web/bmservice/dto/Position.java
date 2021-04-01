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
public class Position {

    private Long account;
    private String symbol;
    private String currency;
    private Date timestamp;
//    private float markPrice;
//    private float lastPrice;
//    private float simpleQty;
    private Float liquidationPrice;

    private Long currentQty;

    List<Position> data;

    public void update(Position newPosition) {
        if(!account.equals(newPosition.account) || !currency.equals(newPosition.currency) || !symbol.equals(newPosition.symbol)) {
            log.info("account:{}, currency:{}, symbol:{}, new account:{}, currency:{}, symbol:{}", account, currency,  symbol, newPosition.account, newPosition.currency, newPosition.symbol);
            throw new RuntimeException("account or currency is different");
        }
        currentQty = (Long)checkUpdate(currentQty, newPosition.currentQty);
        liquidationPrice = (Float)checkUpdate(liquidationPrice, newPosition.liquidationPrice);
        timestamp = newPosition.timestamp;
    }
    private Object checkUpdate(Object older, Object newer) {
        if(newer!=null)
            return newer;
        else return older;
    }



//    "account": "long",
//            "symbol": "symbol",
//            "currency": "symbol",
//            "underlying": "symbol",
//            "quoteCurrency": "symbol",
//            "commission": "float",
//            "initMarginReq": "float",
//            "maintMarginReq": "float",
//            "riskLimit": "long",
//            "leverage": "float",
//            "crossMargin": "boolean",
//            "deleveragePercentile": "float",
//            "rebalancedPnl": "long",
//            "prevRealisedPnl": "long",
//            "prevUnrealisedPnl": "long",
//            "prevClosePrice": "float",
//            "openingTimestamp": "timestamp",
//            "openingQty": "long",
//            "openingCost": "long",
//            "openingComm": "long",
//            "openOrderBuyQty": "long",
//            "openOrderBuyCost": "long",
//            "openOrderBuyPremium": "long",
//            "openOrderSellQty": "long",
//            "openOrderSellCost": "long",
//            "openOrderSellPremium": "long",
//            "execBuyQty": "long",
//            "execBuyCost": "long",
//            "execSellQty": "long",
//            "execSellCost": "long",
//            "execQty": "long",
//            "execCost": "long",
//            "execComm": "long",
//            "currentTimestamp": "timestamp",
//            "currentQty": "long",
//            "currentCost": "long",
//            "currentComm": "long",
//            "realisedCost": "long",
//            "unrealisedCost": "long",
//            "grossOpenCost": "long",
//            "grossOpenPremium": "long",
//            "grossExecCost": "long",
//            "isOpen": "boolean",
//            "markPrice": "float",
//            "markValue": "long",
//            "riskValue": "long",
//            "homeNotional": "float",
//            "foreignNotional": "float",
//            "posState": "symbol",
//            "posCost": "long",
//            "posCost2": "long",
//            "posCross": "long",
//            "posInit": "long",
//            "posComm": "long",
//            "posLoss": "long",
//            "posMargin": "long",
//            "posMaint": "long",
//            "posAllowance": "long",
//            "taxableMargin": "long",
//            "initMargin": "long",
//            "maintMargin": "long",
//            "sessionMargin": "long",
//            "targetExcessMargin": "long",
//            "varMargin": "long",
//            "realisedGrossPnl": "long",
//            "realisedTax": "long",
//            "realisedPnl": "long",
//            "unrealisedGrossPnl": "long",
//            "longBankrupt": "long",
//            "shortBankrupt": "long",
//            "taxBase": "long",
//            "indicativeTaxRate": "float",
//            "indicativeTax": "long",
//            "unrealisedTax": "long",
//            "unrealisedPnl": "long",
//            "unrealisedPnlPcnt": "float",
//            "unrealisedRoePcnt": "float",
//            "simpleQty": "float",
//            "simpleCost": "float",
//            "simpleValue": "float",
//            "simplePnl": "float",
//            "simplePnlPcnt": "float",
//            "avgCostPrice": "float",
//            "avgEntryPrice": "float",
//            "breakEvenPrice": "float",
//            "marginCallPrice": "float",
//            "liquidationPrice": "float",
//            "bankruptPrice": "float",
//            "timestamp": "timestamp",
//            "lastPrice": "float",
//            "lastValue": "long"
}

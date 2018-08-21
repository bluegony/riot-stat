package com.study.bmservice.dto;

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
public class Margin {
    private Long account;
    private String currency;
    private Long riskValue;
    private Long riskLimit;
    private Long amount;
    private Long walletBalance;
    private Long marginBalance;

    private Long excessMargin;
    private Long availableMargin;
    private Long withdrawableMargin;
    private Long initMargin;

    private Date timestamp;

    private float marginLeverage;
    private float marginUsedPcnt;
    private Long grossOpenCost;
    List<Margin> data;

    public void update(Margin newmargin) {
        if(!account.equals(newmargin.account) || !currency.equals(newmargin.currency)) {
            log.info("account:{}, currency:{}, new account:{}, currency:{}", account, currency, newmargin.account, newmargin.currency);
            throw new RuntimeException("account or currency is different");
        }

        riskLimit = checkUpdateLong(riskLimit, newmargin.riskLimit);
        riskValue = checkUpdateLong(riskValue, newmargin.riskValue);
        initMargin = checkUpdateLong(initMargin, newmargin.initMargin);
        amount = checkUpdateLong(amount, newmargin.amount);
        walletBalance = checkUpdateLong(walletBalance, newmargin.walletBalance);
        marginBalance = checkUpdateLong(marginBalance, newmargin.marginBalance);
        excessMargin = checkUpdateLong(excessMargin, newmargin.excessMargin);
        availableMargin = checkUpdateLong(availableMargin, newmargin.availableMargin);
        withdrawableMargin = checkUpdateLong(withdrawableMargin, newmargin.withdrawableMargin);
        grossOpenCost = checkUpdateLong(grossOpenCost, newmargin.grossOpenCost);

        timestamp = newmargin.timestamp;
        marginLeverage = newmargin.marginLeverage;
        marginUsedPcnt = newmargin.marginUsedPcnt;
    }
    private Long checkUpdateLong(Long older, Long newer) {
        if(newer!=null)
            return newer;
        else return older;
    }
    private Object checkUpdate(Object older, Object newer) {
        if(newer!=null)
            return newer;
        else return older;
    }

//     "account": "long",
//             "currency": "symbol",
//             "riskLimit": "long",
//             "prevState": "symbol",
//             "state": "symbol",
//             "action": "symbol",
//             "amount": "long",
//             "pendingCredit": "long",
//             "pendingDebit": "long",
//             "confirmedDebit": "long",
//             "prevRealisedPnl": "long",
//             "prevUnrealisedPnl": "long",
//             "grossComm": "long",
//             "grossOpenCost": "long",
//             "grossOpenPremium": "long",
//             "grossExecCost": "long",
//             "grossMarkValue": "long",
//             "riskValue": "long",
//             "taxableMargin": "long",
//             "initMargin": "long",
//             "maintMargin": "long",
//             "sessionMargin": "long",
//             "targetExcessMargin": "long",
//             "varMargin": "long",
//             "realisedPnl": "long",
//             "unrealisedPnl": "long",
//             "indicativeTax": "long",
//             "unrealisedProfit": "long",
//             "syntheticMargin": "long",
//             "walletBalance": "long",
//             "marginBalance": "long",
//             "marginBalancePcnt": "float",
//             "marginLeverage": "float",
//             "marginUsedPcnt": "float",
//             "excessMargin": "long",
//             "excessMarginPcnt": "float",
//             "availableMargin": "long",
//             "withdrawableMargin": "long",
//             "timestamp": "timestamp",
//             "grossLastValue": "long",
//             "commission": "float"
}

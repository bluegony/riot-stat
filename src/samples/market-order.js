
{
    "table": "order",
    "action": "insert",
    "data": [{
        "orderID": "b88f2590-c147-4e7a-c959-4e5b8518fc97",
        "clOrdID": "",
        "clOrdLinkID": "",
        "account": 423359,
        "symbol": "XBTUSD",
        "side": "Buy",
        "simpleOrderQty": null,
        "orderQty": 1,
        "price": null,
        "displayQty": null,
        "stopPx": null,
        "pegOffsetValue": null,
        "pegPriceType": "",
        "currency": "USD",
        "settlCurrency": "XBt",
        "ordType": "Market",
        "timeInForce": "ImmediateOrCancel",
        "execInst": "",
        "contingencyType": "",
        "exDestination": "XBME",
        "ordStatus": "New",
        "triggered": "",
        "workingIndicator": false,
        "ordRejReason": "",
        "simpleLeavesQty": 0.0002,
        "leavesQty": 1,
        "simpleCumQty": 0,
        "cumQty": 0,
        "avgPx": null,
        "multiLegReportingType": "SingleSecurity",
        "text": "Submission from www.bitmex.com",
        "transactTime": "2018-08-19T07:08:21.774Z",
        "timestamp": "2018-08-19T07:08:21.774Z"
    }]
}




{
    "table": "execution",
    "action": "insert",
    "data": [{
        "execID": "7867fc61-1427-2480-abca-0a062b226362",
        "orderID": "b88f2590-c147-4e7a-c959-4e5b8518fc97",
        "clOrdID": "",
        "clOrdLinkID": "",
        "account": 423359,
        "symbol": "XBTUSD",
        "side": "Buy",
        "lastQty": null,
        "lastPx": null,
        "underlyingLastPx": null,
        "lastMkt": "",
        "lastLiquidityInd": "",
        "simpleOrderQty": null,
        "orderQty": 1,
        "price": 6360.5,
        "displayQty": null,
        "stopPx": null,
        "pegOffsetValue": null,
        "pegPriceType": "",
        "currency": "USD",
        "settlCurrency": "XBt",
        "execType": "New",
        "ordType": "Market",
        "timeInForce": "ImmediateOrCancel",
        "execInst": "",
        "contingencyType": "",
        "exDestination": "XBME",
        "ordStatus": "New",
        "triggered": "",
        "workingIndicator": true,
        "ordRejReason": "",
        "simpleLeavesQty": 0.0002,
        "leavesQty": 1,
        "simpleCumQty": 0,
        "cumQty": 0,
        "avgPx": null,
        "commission": null,
        "tradePublishIndicator": "",
        "multiLegReportingType": "SingleSecurity",
        "text": "Submission from www.bitmex.com",
        "trdMatchID": "00000000-0000-0000-0000-000000000000",
        "execCost": null,
        "execComm": null,
        "homeNotional": null,
        "foreignNotional": null,
        "transactTime": "2018-08-19T07:08:21.774Z",
        "timestamp": "2018-08-19T07:08:21.774Z"
    }]
}




{
    "table": "order",
    "action": "update",
    "data": [{
        "orderID": "b88f2590-c147-4e7a-c959-4e5b8518fc97",
        "price": 6360.5,
        "workingIndicator": true,
        "clOrdID": "",
        "account": 423359,
        "symbol": "XBTUSD",
        "timestamp": "2018-08-19T07:08:21.774Z"
    }]
}




 {
    "table": "position",
    "action": "update",
    "data": [{
        "account": 423359,
        "symbol": "XBTUSD",
        "currency": "XBt",
        "openOrderBuyQty": 1,
        "openOrderBuyCost": -15722,
        "grossOpenCost": 15722,
        "riskValue": 31445,
        "initMargin": 182,
        "timestamp": "2018-08-19T07:08:21.778Z",
        "currentQty": 1,
        "markPrice": 6360.21,
        "simpleQty": 0.0002,
        "liquidationPrice": 9
    }]
}




{
    "table": "margin",
    "action": "update",
    "data": [{
        "account": 423359,
        "currency": "XBt",
        "grossOpenCost": 15722,
        "riskValue": 31445,
        "initMargin": 182,
        "excessMargin": 11193427,
        "availableMargin": 11193427,
        "withdrawableMargin": 11193427,
        "timestamp": "2018-08-19T07:08:21.779Z"
    }]
}




{
    "table": "execution",
    "action": "insert",
    "data": [{
        "execID": "d74214aa-d7b6-1c04-8bff-6dcfa70f0666",
        "orderID": "b88f2590-c147-4e7a-c959-4e5b8518fc97",
        "clOrdID": "",
        "clOrdLinkID": "",
        "account": 423359,
        "symbol": "XBTUSD",
        "side": "Buy",
        "lastQty": 1,
        "lastPx": 6360.5,
        "underlyingLastPx": null,
        "lastMkt": "XBME",
        "lastLiquidityInd": "RemovedLiquidity",
        "simpleOrderQty": null,
        "orderQty": 1,
        "price": 6360.5,
        "displayQty": null,
        "stopPx": null,
        "pegOffsetValue": null,
        "pegPriceType": "",
        "currency": "USD",
        "settlCurrency": "XBt",
        "execType": "Trade",
        "ordType": "Market",
        "timeInForce": "ImmediateOrCancel",
        "execInst": "",
        "contingencyType": "",
        "exDestination": "XBME",
        "ordStatus": "Filled",
        "triggered": "",
        "workingIndicator": false,
        "ordRejReason": "",
        "simpleLeavesQty": 0,
        "leavesQty": 0,
        "simpleCumQty": 0.00015722,
        "cumQty": 1,
        "avgPx": 6360.5,
        "commission": 0.000675,
        "tradePublishIndicator": "PublishTrade",
        "multiLegReportingType": "SingleSecurity",
        "text": "Submission from www.bitmex.com",
        "trdMatchID": "45f5344e-a487-be54-9d7e-0bce51a450e2",
        "execCost": -15722,
        "execComm": 10,
        "homeNotional": 0.00015722,
        "foreignNotional": -1,
        "transactTime": "2018-08-19T07:08:21.774Z",
        "timestamp": "2018-08-19T07:08:21.774Z"
    }]
}






{
    "table": "order",
    "action": "update",
    "data": [{
        "orderID": "b88f2590-c147-4e7a-c959-4e5b8518fc97",
        "ordStatus": "Filled",
        "workingIndicator": false,
        "simpleLeavesQty": 0,
        "leavesQty": 0,
        "simpleCumQty": 0.00015722,
        "cumQty": 1,
        "avgPx": 6360.5,
        "clOrdID": "",
        "account": 423359,
        "symbol": "XBTUSD",
        "timestamp": "2018-08-19T07:08:21.774Z"
    }]
}




{
    "table": "trade",
    "action": "insert",
    "data": [{
        "timestamp": "2018-08-19T07:08:21.774Z",
        "symbol": "XBTUSD",
        "side": "Buy",
        "size": 1,
        "price": 6360.5,
        "tickDirection": "ZeroPlusTick",
        "trdMatchID": "45f5344e-a487-be54-9d7e-0bce51a450e2",
        "grossValue": 15722,
        "homeNotional": 0.00015722,
        "foreignNotional": 1
    }]
}






{
    "table": "position",
    "action": "update",
    "data": [{
        "account": 423359,
        "symbol": "XBTUSD",
        "currency": "XBt",
        "openOrderBuyQty": 0,
        "openOrderBuyCost": 0,
        "execBuyQty": 2,
        "execBuyCost": 31403,
        "execQty": 2,
        "execCost": -31403,
        "execComm": 20,
        "currentTimestamp": "2018-08-19T07:08:21.781Z",
        "currentQty": 2,
        "currentCost": -31403,
        "currentComm": 20,
        "unrealisedCost": -31403,
        "grossOpenCost": 0,
        "grossExecCost": 31403,
        "markValue": -31446,
        "riskValue": 31446,
        "homeNotional": 0.00031446,
        "foreignNotional": -2,
        "posCost": -31403,
        "posCost2": -31403,
        "posInit": 315,
        "posComm": 24,
        "posMargin": 381,
        "posMaint": 182,
        "initMargin": 0,
        "maintMargin": 338,
        "realisedPnl": -20,
        "unrealisedGrossPnl": -43,
        "unrealisedPnl": -43,
        "unrealisedPnlPcnt": -0.0014,
        "unrealisedRoePcnt": -0.1369,
        "simpleQty": 0.0003,
        "simpleCost": 2,
        "simpleValue": 2,
        "avgCostPrice": 6369.0211,
        "avgEntryPrice": 6369.0211,
        "breakEvenPrice": 6373.5,
        "marginCallPrice": 18,
        "liquidationPrice": 18,
        "bankruptPrice": 18,
        "timestamp": "2018-08-19T07:08:21.781Z",
        "lastValue": -31446,
        "markPrice": 6360.21
    }]
}




{
    "table": "margin",
    "action": "update",
    "data": [{
        "account": 423359,
        "currency": "XBt",
        "grossComm": 20,
        "grossOpenCost": 0,
        "grossExecCost": 31403,
        "grossMarkValue": 31446,
        "riskValue": 31446,
        "initMargin": 0,
        "maintMargin": 338,
        "realisedPnl": -20,
        "unrealisedPnl": -43,
        "walletBalance": 11193810,
        "marginBalance": 11193767,
        "marginLeverage": 0.002809241964746988,
        "excessMargin": 11193429,
        "availableMargin": 11193429,
        "withdrawableMargin": 11193429,
        "timestamp": "2018-08-19T07:08:21.783Z",
        "grossLastValue": 31446
    }]
}
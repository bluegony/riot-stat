{
  "table": "margin",
  "action": "partial",
  "keys": [
    "account",
    "currency"
  ],
  "types": {
    "account": "long",
    "currency": "symbol",
    "riskLimit": "long",
    "prevState": "symbol",
    "state": "symbol",
    "action": "symbol",
    "amount": "long",
    "pendingCredit": "long",
    "pendingDebit": "long",
    "confirmedDebit": "long",
    "prevRealisedPnl": "long",
    "prevUnrealisedPnl": "long",
    "grossComm": "long",
    "grossOpenCost": "long",
    "grossOpenPremium": "long",
    "grossExecCost": "long",
    "grossMarkValue": "long",
    "riskValue": "long",
    "taxableMargin": "long",
    "initMargin": "long",
    "maintMargin": "long",
    "sessionMargin": "long",
    "targetExcessMargin": "long",
    "varMargin": "long",
    "realisedPnl": "long",
    "unrealisedPnl": "long",
    "indicativeTax": "long",
    "unrealisedProfit": "long",
    "syntheticMargin": "long",
    "walletBalance": "long",
    "marginBalance": "long",
    "marginBalancePcnt": "float",
    "marginLeverage": "float",
    "marginUsedPcnt": "float",
    "excessMargin": "long",
    "excessMarginPcnt": "float",
    "availableMargin": "long",
    "withdrawableMargin": "long",
    "timestamp": "timestamp",
    "grossLastValue": "long",
    "commission": "float"
  },
  "foreignKeys": {},
  "attributes": {
    "account": "sorted",
    "currency": "grouped"
  },
  "filter": {
    "account": 423359
  },
  "data": [
    {
      "account": 423359,
      "currency": "XBt",
      "riskLimit": 1000000000000,
      "prevState": "",
      "state": "",
      "action": "",
      "amount": 11193830,
      "pendingCredit": 0,
      "pendingDebit": 0,
      "confirmedDebit": 0,
      "prevRealisedPnl": 694350,
      "prevUnrealisedPnl": 0,
      "grossComm": 10,
      "grossOpenCost": 0,
      "grossOpenPremium": 0,
      "grossExecCost": 15681,
      "grossMarkValue": 15669,
      "riskValue": 15669,
      "taxableMargin": 0,
      "initMargin": 0,
      "maintMargin": 181,
      "sessionMargin": 0,
      "targetExcessMargin": 0,
      "varMargin": 0,
      "realisedPnl": -10,
      "unrealisedPnl": 12,
      "indicativeTax": 0,
      "unrealisedProfit": 0,
      "syntheticMargin": 0,
      "walletBalance": 11193820,
      "marginBalance": 11193832,
      "marginBalancePcnt": 1,
      "marginLeverage": 0.0013997887407993974,
      "marginUsedPcnt": 0,
      "excessMargin": 11193651,
      "excessMarginPcnt": 1,
      "availableMargin": 11193651,
      "withdrawableMargin": 11193651,
      "timestamp": "2018-08-19T06:42:15.322Z",
      "grossLastValue": 15669,
      "commission": null
    }
  ]
}
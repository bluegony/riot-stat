{
  "table": "quote",
  "action": "partial",
  "keys": [],
  "types": {
    "timestamp": "timestamp",
    "symbol": "symbol",
    "bidSize": "long",
    "bidPrice": "float",
    "askPrice": "float",
    "askSize": "long"
  },
  "foreignKeys": {
    "symbol": "instrument"
  },
  "attributes": {
    "timestamp": "sorted",
    "symbol": "grouped"
  },
  "filter": {
    "symbol": "XBTUSD"
  },
  "data": [
    {
      "timestamp": "2018-08-19T06:44:38.439Z",
      "symbol": "XBTUSD",
      "bidSize": 581633,
      "bidPrice": 6379,
      "askPrice": 6379.5,
      "askSize": 551287
    }
  ]
}
package com.study.bmservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Instrument {

    private Date timestamp;
    @JsonProperty("bidPrice")
    private float buyPrice;
    @JsonProperty("askPrice")
    private float sellPrice;
    private float lastPrice;
    private List<Instrument> data;
    private String userCode;

    public boolean isValidPriceData() {
        if(lastPrice!=0 || buyPrice!=0 || sellPrice!=0)
            return true;
        return false;
    }

//    "symbol": "symbol",
//            "rootSymbol": "symbol",
//            "state": "symbol",
//            "typ": "symbol",
//            "listing": "timestamp",
//            "front": "timestamp",
//            "expiry": "timestamp",
//            "settle": "timestamp",
//            "relistInterval": "timespan",
//            "inverseLeg": "symbol",
//            "sellLeg": "symbol",
//            "buyLeg": "symbol",
//            "optionStrikePcnt": "float",
//            "optionStrikeRound": "float",
//            "optionStrikePrice": "float",
//            "optionMultiplier": "float",
//            "positionCurrency": "symbol",
//            "underlying": "symbol",
//            "quoteCurrency": "symbol",
//            "underlyingSymbol": "symbol",
//            "reference": "symbol",
//            "referenceSymbol": "symbol",
//            "calcInterval": "timespan",
//            "publishInterval": "timespan",
//            "publishTime": "timespan",
//            "maxOrderQty": "long",
//            "maxPrice": "float",
//            "lotSize": "long",
//            "tickSize": "float",
//            "multiplier": "long",
//            "settlCurrency": "symbol",
//            "underlyingToPositionMultiplier": "long",
//            "underlyingToSettleMultiplier": "long",
//            "quoteToSettleMultiplier": "long",
//            "isQuanto": "boolean",
//            "isInverse": "boolean",
//            "initMargin": "float",
//            "maintMargin": "float",
//            "riskLimit": "long",
//            "riskStep": "long",
//            "limit": "float",
//            "capped": "boolean",
//            "taxed": "boolean",
//            "deleverage": "boolean",
//            "makerFee": "float",
//            "takerFee": "float",
//            "settlementFee": "float",
//            "insuranceFee": "float",
//            "fundingBaseSymbol": "symbol",
//            "fundingQuoteSymbol": "symbol",
//            "fundingPremiumSymbol": "symbol",
//            "fundingTimestamp": "timestamp",
//            "fundingInterval": "timespan",
//            "fundingRate": "float",
//            "indicativeFundingRate": "float",
//            "rebalanceTimestamp": "timestamp",
//            "rebalanceInterval": "timespan",
//            "openingTimestamp": "timestamp",
//            "closingTimestamp": "timestamp",
//            "sessionInterval": "timespan",
//            "prevClosePrice": "float",
//            "limitDownPrice": "float",
//            "limitUpPrice": "float",
//            "bankruptLimitDownPrice": "float",
//            "bankruptLimitUpPrice": "float",
//            "prevTotalVolume": "long",
//            "totalVolume": "long",
//            "volume": "long",
//            "volume24h": "long",
//            "prevTotalTurnover": "long",
//            "totalTurnover": "long",
//            "turnover": "long",
//            "turnover24h": "long",
//            "homeNotional24h": "float",
//            "foreignNotional24h": "float",
//            "prevPrice24h": "float",
//            "vwap": "float",
//            "highPrice": "float",
//            "lowPrice": "float",
//            "lastPrice": "float",
//            "lastPriceProtected": "float",
//            "lastTickDirection": "symbol",
//            "lastChangePcnt": "float",
//            "bidPrice": "float",
//            "midPrice": "float",
//            "askPrice": "float",
//            "impactBidPrice": "float",
//            "impactMidPrice": "float",
//            "impactAskPrice": "float",
//            "hasLiquidity": "boolean",
//            "openInterest": "long",
//            "openValue": "long",
//            "fairMethod": "symbol",
//            "fairBasisRate": "float",
//            "fairBasis": "float",
//            "fairPrice": "float",
//            "markMethod": "symbol",
//            "markPrice": "float",
//            "indicativeTaxRate": "float",
//            "indicativeSettlePrice": "float",
//            "optionUnderlyingPrice": "float",
//            "settledPrice": "float",
//            "timestamp": "timestamp"
}
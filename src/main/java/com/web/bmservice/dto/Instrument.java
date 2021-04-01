package com.web.bmservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.util.List;

@Slf4j
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Instrument {

    private Instant utcDateTime;
    private LocalDateTime timestamp;
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

    public boolean hasLastPrice() {

        /**
         * timestamp값이 set 된후 변환.
         * objectmapper의 setter 또는 post process로 등록해야하는데.. 일단 여기서 처리
         */
        utcDateTime = timestamp.toInstant(ZoneOffset.UTC);  // localdatetime으로부터 UTC 시각을 추출.
        timestamp = utcDateTime.atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime();  // UTC 시각으로부터 필요한 timezone 시각으로 변환

        if(lastPrice!=0)
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

package com.web.bmservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * Created on 2018. 8. 21..
 */
@Slf4j
@Getter
@Setter
@ToString
public class TrueRange {
    private LocalDateTime timestamp;
    private float lastPrice;
    private float highPrice;
    private float lowPrice;

    public float getTrueRange() {
        log.info("last:{} high:{} low:{}", lastPrice,highPrice,lowPrice);
        float answer = 0;
        if(lastPrice>highPrice) {
            answer = lastPrice-lowPrice;
        } else if (lastPrice<lowPrice) {
            answer = highPrice-lastPrice;
        } else {
            answer = highPrice-lowPrice;
        }
        log.info("answer={}",answer);
        return answer;
    }
}

package com.study.bmservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@ToString
public class Price {
    private Date timestamp;
    private float highPrice;
    private float lowPrice;
    private float lastPrice;
    private String userCode;

    public void setData(Instrument instrument) {
        if(this.timestamp==null || checkAnotherMinute(instrument) ) {
            log.debug("set new data");
            LocalDateTime localDateTime = instrument.getTimestamp().toInstant().truncatedTo(ChronoUnit.MINUTES)
                    .atZone(ZoneId.of("Asia/Seoul"))
                    .toLocalDateTime();
            timestamp =  java.sql.Timestamp.valueOf(localDateTime);
            lastPrice = lowPrice = highPrice = instrument.getLastPrice();
        } else {
            if(this.highPrice<instrument.getLastPrice())
                this.highPrice = instrument.getLastPrice();
            if(this.lowPrice>instrument.getLastPrice())
                this.lowPrice = instrument.getLastPrice();
            this.lastPrice = instrument.getLastPrice();
        }
        this.userCode = instrument.getUserCode();
    }

    private boolean checkAnotherMinute(Instrument instrument) {

        if(this.timestamp==null)
            return true;

        log.debug("this:{} inst:{}",timestamp, instrument.getTimestamp());


        LocalDateTime prevTime = this.timestamp.toInstant().truncatedTo(ChronoUnit.MINUTES)
                .atZone(ZoneId.systemDefault()) // 이미 변환된 시각이므로 여기서 또 변환(+9시간)하면 nextTime과 비교할때 오류
                .toLocalDateTime();
        log.debug("{}",prevTime);

        LocalDateTime nextTime = instrument.getTimestamp().toInstant().truncatedTo(ChronoUnit.MINUTES)
                .atZone(ZoneId.of("Asia/Seoul"))
                .toLocalDateTime();
        log.debug("{}",nextTime);
        if(Objects.equals(prevTime,nextTime)) {

            log.debug("equal minute");
            return false;
        }
        log.debug("not equal");
        return true;
    }
}

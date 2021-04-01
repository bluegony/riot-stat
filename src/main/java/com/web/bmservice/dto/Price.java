package com.web.bmservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@ToString
public class Price {

    private LocalDateTime timestamp;
    private float highPrice;
    private float lowPrice;
    private float lastPrice;
    private String userCode;

    public void setData(Instrument instrument) {
        if(this.timestamp==null || checkAnotherMinute(instrument) ) {
            log.debug("set new data");
            timestamp = instrument.getTimestamp().truncatedTo(ChronoUnit.MINUTES);
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

        LocalDateTime prevTime = this.timestamp;
        LocalDateTime nextTime = instrument.getTimestamp().truncatedTo(ChronoUnit.MINUTES);

        if(Objects.equals(prevTime,nextTime)) {
            log.debug("equal minute");
            return false;
        }
        log.info("change price minute [{}] to [{}] ",prevTime,nextTime);
        return true;
    }
}

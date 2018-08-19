package com.study.bmservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CommonTest {

    @Test
    public void test() {
        float highPrice = 6393.0f;
        float lastPrice = 6392.5f;
        if(highPrice<lastPrice)
            highPrice = lastPrice;
        log.info("{}",highPrice);

    }
}

package com.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by 1000773 on 2017. 12. 11..
 */
@Slf4j
public class CipherUtilsTest {

    @Test
    public void encrypt() throws Exception {
        String enc = CipherUtils.encrypt("", "");
        log.info(enc);
        String dec = CipherUtils.decrypt(enc, "");
        log.info(dec);
    }

}
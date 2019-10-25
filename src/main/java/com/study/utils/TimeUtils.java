package com.study.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2019. 6. 25..
 */
@Slf4j
public class TimeUtils {

    private long showProcessingTime(long startTime, String processName) {
        long endTime = System.currentTimeMillis();
        float diffSeconds = (endTime - startTime) / 1000.0f;
        String diffTime = String.format("%.3fs", diffSeconds);


        log.info(StringTools.prettyCommentTitle(String.format("startTime:[%d], endTime:[%d], diffTime:[%s]  - process:[%s]", startTime, endTime, diffTime, processName)));
//        log.info("startTime:[{}], endTime:[{}], diffTime:[{}]  - process:[{}]", startTime, endTime, diffTime, processName);
        return endTime;
    }

}

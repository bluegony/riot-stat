package com.web.dto.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 2019. 4. 29..
 */
@Slf4j
//@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class InnerClassTest {

    @Test
    public void test() {
        DataInputRequest d = new DataInputRequest();
//        d.reqData = d.new ReqData();
        d.reqData = new DataInputRequest.ReqData();
        d.reqData.testString = "dkdkdk";

        log.info("{}", d.reqData.testString);


        DataInputRequest e = new DataInputRequest();
//        e.reqData = e.new ReqData();
        e.reqData = new DataInputRequest.ReqData();
        e.reqData.testString = "qwqwqw";
        log.info("{}", e.reqData.testString);

        log.info("{}", d.reqData.testString);

        log.info("{}", DataInputRequest.ReqData.testString);


    }



}
package com.web.riotservice;


import com.web.config.AppConfig;
import com.web.config.DatabaseConfig;
import com.web.utils.ByteBufferWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by bluegony on 2018. 6. 18..
 */
//@ExtendWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfig.class, DatabaseConfig.class})
@ContextConfiguration(classes={RiotService.class, RiotStatJob.class, ChampUtil.class})
public class RiotServiceTest {

    @Autowired
    RiotService riotService;
    @Autowired
    RiotStatJob riotStatJob;
    @Autowired
    ChampUtil champUtil;


    /**
     * https://developer.riotgames.com/api-methods/#summoner-v3/GET_getBySummonerName
     */
    @Test
    public void readRiot() {
        riotStatJob.readRiot();
    }

    /**
     * https://developer.riotgames.com/api-methods/#champion-v3/GET_getChampions
     */
    @Test
    public void readChamp() {
        champUtil.championData();
    }

    @Test
    public void checkEncoding() {
        ByteBufferWrapper b = new ByteBufferWrapper(1);
//        b.checkKoreanEncodingProblem("諛붿씠");
        b.checkKoreanEncodingProblem("바이");

    }



}
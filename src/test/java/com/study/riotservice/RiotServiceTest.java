package com.study.riotservice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by bluegony on 2018. 6. 18..
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {AppConfig.class, DatabaseConfig.class})
@SpringBootTest
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



}
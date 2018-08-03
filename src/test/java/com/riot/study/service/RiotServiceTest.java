package com.riot.study.service;


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
    ChampUtil champUtil;

    /**
     * https://developer.riotgames.com/api-methods/#summoner-v3/GET_getBySummonerName
     */
    @Test
    public void readRiot() {
        champUtil.championData();
        riotService.read(686052, 1 );      // 푸른고니움
        riotService.read(204587293, 1);   // 푸른고니누
        riotService.read(208797470, 1);   // 칼국수바람
        riotService.read(209910369, 1);   // 구운아몬드
        riotService.read(204092236,2); // stormpin
//        riotService.read(5060628, 3);     // Norizi
//        riotService.read(3440481,4);    // hide no bush
    }

    /**
     * https://developer.riotgames.com/api-methods/#champion-v3/GET_getChampions
     */
    @Test
    public void readChamp() {
        champUtil.championData();
    }



}
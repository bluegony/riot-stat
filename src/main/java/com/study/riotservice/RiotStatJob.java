package com.study.riotservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RiotStatJob {

    @Autowired
    RiotService riotService;

    public void readRiot() {
//        champUtil.championData();
        riotService.read(686052, 1 );     // 푸른고니움
        riotService.read(204587293, 1);   // 푸른고니누
        riotService.read(208797470, 1);   // 칼국수바람
        riotService.read(209910369, 1);   // 구운아몬드
        riotService.read(204092236,2);      // stormpin
        riotService.read(205356270, 2);     // 알카드07
//        riotService.read(5060628, 3);     // Norizi
//        riotService.read(3440481,4);    // hide no bush
    }

}

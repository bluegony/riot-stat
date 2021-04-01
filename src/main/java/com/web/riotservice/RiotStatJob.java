package com.web.riotservice;

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
//        riotService.read4('91Ryy6wResxKvDzkoPhZc8R38l71pUwr9GadamJw-WXGobE', 5);     // 커널킬러
//        riotService.read4('kZ9ksgwapL3DySDzmuXQVNI3T3IjT510hS4SR9H6y0zF-_I', 5);     // 커널킬너
//        riotService.read('0EDpBTnxqjbd1mqpNV3VfBr_cm9QpSGt6Gh0wp6NoCn1', 6);     // 김아니
//        riotService.read(5060628, 3);     // Norizi
//        riotService.read(3440481,4);    // hide no bush
    }

}

//package com.skplanet.study.service;
//
//import com.skplanet.study.service.dto.Level0;
//import com.skplanet.study.utils.JsonUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Created by 1000773 on 2018. 6. 15..
// */
//@Slf4j
//@Service
//public class RiotService1 {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    private static String matchApi = "https://acs.leagueoflegends.com/v1/stats/player_history/KR/%d?begIndex=%d&endIndex=%d&";
//    private static String championApi = "https://acs.leagueoflegends.com/v1/stats/player_history/KR/%d?begIndex=%d&endIndex=%d&";
//    // 686052
//
//
//    public void read()  {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//
////        Level0 result = restTemplate.getForObject(String.format(matchApi, 686052, 600, 920), Level0.class);
////        log.info(JsonUtils.objectToPrettyJson(result));
//
//        List<Match> matchList = new ArrayList<>();
//
//        for(int i=0; ; i=i+20) {
//            Level0 result = restTemplate.getForObject(String.format(matchApi, 686052, i, i+20), Level0.class);
//            int size = result.getGames().getGames().size();
//
//            matchList.addAll(result.convert());
//
//
//            if(size<=20) break;
//            try {
//                Thread.sleep(200);
//            } catch (Exception e) {
//
//            }
////            log.info(JsonUtils.objectToPrettyJson(result));
//        }
//
////        for(Match match:matchList) {
////            log.info(match.toString());
////        }
//        log.info("size = {}", matchList.size());
////        matchList.stream().forEach(m->{log.info(m.toString());});
//        matchList.stream().filter(m->m.getGameMode().equals("ARAM")).forEach(m->{log.info(m.toString());});
//
////        for(Match m :matchList.stream().filter(m->m.getGameType().equals("ARAM")).collect(Collectors.toList())) {
////            log.info(m.toString());
////        }
//
//    }
//
//}

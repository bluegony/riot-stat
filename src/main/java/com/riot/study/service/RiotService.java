package com.riot.study.service;

import com.riot.study.service.dto.LMatch;
import com.riot.study.service.dto.Level0;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 1000773 on 2018. 6. 18..
 */
@Slf4j
@Service
public class RiotService {

    @Autowired
    TempStatMapper tempStatMapper;

    private static String matchApi = "https://acs.leagueoflegends.com/v1/stats/player_history/KR/%d?begIndex=%d&endIndex=%d&";
    private static String championApi = "https://acs.leagueoflegends.com/v1/stats/player_history/KR/%d?begIndex=%d&endIndex=%d&";

    // https://acs.leagueoflegends.com/v1/stats/player_history/KR/209910369?begIndex=90&endIndex=970&
    // https://wol.gg/stats/kr/norizi/


    public void read(int accountId, int owner) {
        read(accountId, owner,200);
    }
    /**
     * https://developer.riotgames.com/api-methods/#summoner-v3/GET_getBySummonerName
     */
    public void read(int accountId, int owner, Integer limit)  {

        RestTemplate restTemplate = new RestTemplate();


        for(int i=0; ; i=i+20) {
            Level0 result = restTemplate.getForObject(String.format(matchApi, accountId, i, i+20), Level0.class);
            int size = result.getGames().getGames().size();

            int count = -1;
            for(LMatch match:result.convert()) {
                if(match.getGameMode().equals("ARAM") && match.getGameType().equals("MATCHED_GAME")) {
                    match.setOwner(owner);
                    log.info(match.toString());
                    count = tempStatMapper.insertTempStat(match);
                }
            }

//            if(size<20 ) {
            if(size<20 || limit!=null&&i+20>=limit || count<1) {
                log.info("Break!!! size = {}, count = {}, limit={} i+20={}", size, count, limit, i+20);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {

            }
        }
//        matchList.stream().forEach(m->{log.info(m.toString());});
//        matchList.stream().filter(m->m.getGameMode().equals("ARAM")).forEach(m->{log.info(m.toString());});

//        for(Match m :matchList.stream().filter(m->m.getGameType().equals("ARAM")).collect(Collectors.toList())) {
//            log.info(m.toString());
//        }

    }

}

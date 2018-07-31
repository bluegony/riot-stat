package com.riot.study.service.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bluegony on 2018. 6. 15..
 */
@Slf4j
@Getter
public class Level0 {
    private long accountId;
    private Level1 games;

    public List<LMatch> convert() {

        List<LMatch> matchList = new ArrayList<>();

        for(Level2 data:games.getGames()) {
            matchList.add(new LMatch(data));
        }
        log.info("match size = {}", matchList.size());
        if(matchList.size()==0)
            throw new RuntimeException("match size is 0");
        return matchList;
    }

}

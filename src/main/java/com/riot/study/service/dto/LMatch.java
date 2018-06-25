package com.riot.study.service.dto;

import com.riot.study.service.ChampUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by 1000773 on 2018. 6. 15..
 */
@ToString
@Getter
@Slf4j
@Setter
public class LMatch {
    private long accountId;
    private long owner;
    private long currentAccountId;
    private long summonerId;
    private String summonerName;
    private long gameId;
    private int championId;
    private String championName;
    private boolean win;
    private boolean lose;
    private long gameDuration;
    private int seasonId;
    private String gameVersion;
    private String gameMode;        // "ARAM"
    private String gameType;        // "MATCHED_GAME"
    private long gameCreation;
    private Date startTime;


    public LMatch(Level2 data) {
        gameCreation = data.getGameCreation();
        startTime = Date.from( LocalDateTime.ofInstant(Instant.ofEpochMilli(gameCreation),
                TimeZone.getDefault().toZoneId()).atZone(ZoneId.systemDefault()).toInstant());

//        startTime =  LocalDateTime.ofInstant(Instant.ofEpochMilli(gameCreation),
//                        TimeZone.getDefault().toZoneId());
        gameDuration = data.getGameDuration();
        gameId = data.getGameId();
        gameMode = data.getGameMode();
        gameType = data.getGameType();
        gameVersion = data.getGameVersion();
        seasonId = data.getSeasonId();
        championId = data.getParticipants().get(0).getChampionId();
//        championName = ChampUtil.championData().get(championId);
        win = data.getParticipants().get(0).getStats().isWin();
        lose = !win;
        Level3_2_1 data321 = data.getParticipantIdentities().get(0).getPlayer();
        accountId = data321.getAccountId();
        currentAccountId = data321.getCurrentAccountId();
        summonerId = data321.getSummonerId();
        summonerName = data321.getSummonerName().substring(data321.getSummonerName().length()-1);

//        accountId = data.getAccountId();
//        Level2 l1data = data.getGames().getGames()[0];
//        l1data.getGames()
    }
}

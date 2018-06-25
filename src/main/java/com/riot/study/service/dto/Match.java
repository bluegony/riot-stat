//package com.skplanet.study.service;
//
//import com.skplanet.study.service.dto.Level0;
//import com.skplanet.study.service.dto.Level1;
//import com.skplanet.study.service.dto.Level2;
//import com.skplanet.study.service.dto.Level3_2_1;
//import lombok.Getter;
//import lombok.ToString;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.TimeZone;
//
///**
// * Created by bluegony on 2018. 6. 15..
// */
//@ToString
//@Getter
//public class Match {
//    private long accountId;
//    private String summonerName;
//    private long summonerId;
//    private long currentAccountId;
//    private boolean win;
//    private long championId;
//    private long gameId;
//    private long gameCreation;
//    private LocalDateTime gameStartTime;
//    private long gameDuration;
//    private int seasonId;
//    private String gameVersion;
//    private String gameMode;        // "ARAM"
//    private String gameType;        // "MATCHED_GAME"
//
//
//    public Match(Level2 data) {
//        gameCreation = data.getGameCreation();
//        gameStartTime =  LocalDateTime.ofInstant(Instant.ofEpochMilli(gameCreation),
//                        TimeZone.getDefault().toZoneId());
//        gameDuration = data.getGameDuration();
//        gameId = data.getGameId();
//        gameMode = data.getGameMode();
//        gameType = data.getGameType();
//        gameVersion = data.getGameVersion();
//        seasonId = data.getSeasonId();
//        championId = data.getParticipants().get(0).getChampionId();
//        win = data.getParticipants().get(0).getStats().isWin();
//        Level3_2_1 data321 = data.getParticipantIdentities().get(0).getPlayer();
//        accountId = data321.getAccountId();
//        currentAccountId = data321.getCurrentAccountId();
//        summonerId = data321.getSummonerId();
//        summonerName = data321.getSummonerName();
//
////        accountId = data.getAccountId();
////        Level2 l1data = data.getGames().getGames()[0];
////        l1data.getGames()
//    }
//}

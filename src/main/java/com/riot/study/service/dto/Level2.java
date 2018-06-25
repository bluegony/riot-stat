package com.riot.study.service.dto;

import lombok.Getter;

import java.util.List;

/**
 * Created by bluegony on 2018. 6. 15..
 */
@Getter
public class Level2 {
    private long gameId;
    private long gameCreation;
    private long gameDuration;
    private int seasonId;
    private String gameVersion;
    private String gameMode;        // "ARAM"
    private String gameType;        // "MATCHED_GAME"

    private List<Level3_1> participants;

    private List<Level3_2> participantIdentities;

}

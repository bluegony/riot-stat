package com.web.riotservice.dto;

import lombok.Getter;

import java.util.List;

/**
 * Created by bluegony on 2018. 6. 15..
 */
@Getter
public class Level1 {
    private long gameCount;
    private List<Level2> games;
}

package com.riot.study.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

/**
 * Created by bluegony on 2018. 6. 15..
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Champion {
    private List<Champion> data;
    private String title;
    private String key;
    private String name;
    private int id;
}

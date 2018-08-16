package com.study.bmservice.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Quote {

    private LocalDateTime timestamp;
    private long bidSize;
    private float bidPrice;
    private float askPrice;
    private long askSize;
    private List<Quote> data;

}

package com.study.utils.qadata.dto;

import lombok.Getter;

/**
 * Created by 1000773 on 2019. 1. 18..
 */
@Getter
public class SimpleSuccessFailDto {
    private int success;
    private int fail;

    public SimpleSuccessFailDto(int success, int fail) {
        this.success = success;
        this.fail = fail;
    }

    public SimpleSuccessFailDto() {
    }
}

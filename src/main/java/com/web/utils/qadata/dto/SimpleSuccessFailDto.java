package com.web.utils.qadata.dto;

import lombok.Getter;

/**
 * Created on 2019. 1. 18..
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

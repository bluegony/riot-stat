package com.web.dto;

import lombok.Getter;

@Getter
public class TrObject {

    private String issueStatus = "1";

    public String resultCode() {
        return "0001";
    }
}

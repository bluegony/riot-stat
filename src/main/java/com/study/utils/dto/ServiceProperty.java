package com.study.utils.dto;

import lombok.Getter;

import java.util.Date;

/**
 * Created by 1000773 on 2018. 8. 16..
 */
@Getter
public class ServiceProperty {
    private int id;
    private String prkey;
    private String subkey;
    private String value;
    private String description;
    private Date createdDate;

    public ServiceProperty(String prkey, String subkey) {
        this.prkey = prkey;
        this.subkey = subkey;
    }

    public ServiceProperty() {
    }
}

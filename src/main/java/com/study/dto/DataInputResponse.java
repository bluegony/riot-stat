package com.study.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Created on 2019. 4. 29..
 */
public class DataInputResponse implements ResponseCommon {

    class ResData {

        @JsonProperty("ResHeader")
        ResHeader resHeader;

        @JsonProperty("ReqBody")
        ResBody resBody;

    }

    @Getter
    class ResHeader {
        private String rtnCode;
        private String rtnMessage;
        private String command;
        private String commmandVersion;

    }

    @Getter
    class ResBody {
        private String encData;


    }

    @Getter
    @JsonProperty("ResData")
    private ResData resData;

    DataInputResponse(DataInputRequest request) {
        resData = new ResData();
    }

}

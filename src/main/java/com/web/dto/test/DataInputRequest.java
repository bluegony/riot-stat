package com.web.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Created on 2019. 4. 29..
 */
public class DataInputRequest implements RequestCommon {

    static class ReqData {

        @JsonProperty("ReqHeader")
        ReqHeader reqHeader;

        @JsonProperty("ReqBody")
        ReqBody reqBody;

        static String testString;

    }

    static class ReqHeader {
        private String mdn;
        private String authKey;
        private String os;
        private String osVersion;
        private String appVersion;
        private String carrier;
        private String accessChannel;
        private String deviceModel;
        private String marketCd;
        private String logSessionId;
        private String command;
        private String commmandVersion;

    }

    static class ReqBody {
        private String encData;


    }

    @Getter
    @JsonProperty("ReqData")
    ReqData reqData;

    @Override
    public ResponseCommon processRequest() {
        return new DataInputResponse(this);

    }

}

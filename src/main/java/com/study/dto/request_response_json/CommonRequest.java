package com.study.dto.request_response_json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.utils.JsonUtils;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by 1000773 on 2019. 4. 29..
 */
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonRequest {

    @Getter
    private RequestData requestData;

    @Getter
    public static class RequestData {

        @JsonProperty("ReqHeader")
        private ReqHeader reqHeader;

        @JsonProperty("ReqBody")
        private Object reqBody;

    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReqHeader {
        private String memberId;
    }

    public Object getReqBody() {
        return requestData.reqBody;
    }
    public <T> T getReqBody( Class<T> valueType) {
        return JsonUtils.objectToClass(requestData.reqBody, valueType);
    }
    public ReqHeader getReqHeader() {
        return requestData.reqHeader;
    }



}

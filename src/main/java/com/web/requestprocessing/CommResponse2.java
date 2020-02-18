package com.web.requestprocessing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class CommResponse2 {
    private String traceNo;
    private Object resData;
//    private Object reqData;
}

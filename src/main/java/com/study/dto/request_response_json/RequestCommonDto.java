package com.study.dto.request_response_json;

import com.skplanet.pfms.api.service.ServiceHolder;
import com.skplanet.pfms.common.dto.CommonRequest;

/**
 * Created by 1000773 on 2019. 7. 4..
 */
public abstract class RequestCommonDto extends CommonRequest {

//    private String traceNo;
    public abstract Object processRequest(ServiceHolder serviceHolder);

}
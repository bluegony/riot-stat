package com.study.dto.request_response_json;

import com.skplanet.pfms.common.exception.enums.ResultCodeAndMessage;
import com.skplanet.pfms.common.filter.tracking.TransactionContextHolder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonResponse {

    protected String responseCode = ResultCodeAndMessage.SUCCESS.getCode();
    protected String responseMessage = ResultCodeAndMessage.SUCCESS.getMessage();
    protected String transactionId; // 트랜잭션 ID
    protected Object responseData; // response 내용

    public CommonResponse(Object responseData) {
        transactionId = TransactionContextHolder.getTransactionId();
        this.responseData = responseData;
    }

    public CommonResponse() {
        transactionId = TransactionContextHolder.getTransactionId();
    }

}

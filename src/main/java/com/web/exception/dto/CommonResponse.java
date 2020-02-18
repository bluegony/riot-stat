package com.web.exception.dto;

import com.web.exception.enu.ErrorCode;
import com.web.filter.TransactionContextHolder;
import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class CommonResponse {

    protected String code = ErrorCode.SUCCESS.getCode();

    protected String message = ErrorCode.SUCCESS.getMessage();

    protected String transactionId;

    public CommonResponse() {
        transactionId = TransactionContextHolder.getTransactionId();
    }

}

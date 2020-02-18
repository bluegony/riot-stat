package com.web.exception.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.web.exception.enu.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by 1000773 on 2017. 6. 9..
 */
@Getter
@Setter
@JsonPropertyOrder({"code","message","detailMessage","detailMessage","transactionId","resultData"})
public class ErrorResponse extends CommonResponse {

    private String detailMessage;
    private Object resultData;

    public ErrorResponse(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    public ErrorResponse(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.resultData = data;
    }

    public ErrorResponse(ErrorCode e, Object data) {
        super();
        this.code = e.getCode();
        this.message = e.getMessage();
        this.resultData = data;
    }

    public ErrorResponse(ErrorCode e, String detailMessage, Object data) {
        super();
        this.code = e.getCode();
        this.message = e.getMessage();
        this.detailMessage = detailMessage;
        this.resultData = data;
    }

}

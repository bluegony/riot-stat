package com.web.exception.custom;


import com.web.exception.enu.ErrorCode;

/**
 * Created by 1000773 on 2017. 7. 26..
 */
public class SimpleException extends CustomException {
    public SimpleException(int httpStatusCode, String code, String message) {
        super(httpStatusCode, code, message);
    }

    public SimpleException(ErrorCode e) {
        super(e);
    }

    public SimpleException(ErrorCode e, Object data) {
        super(e, data);
    }

    public SimpleException(ErrorCode e, String detailMessage, Object data) {
        super(e,detailMessage,data);
    }
}

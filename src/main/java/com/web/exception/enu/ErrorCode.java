package com.web.exception.enu;

import lombok.Getter;

/**
 * 공통 : 에러 코드 정의
 */
@Getter
public enum ErrorCode {

    // 성공
    SUCCESS(200, "0000", "SUCCESS"),

    PAGE_NOT_FOUND(900, "900", "에러가 발생하였습니다." ),


    BAD_REQUEST_CONSTRAINT_VIOLATION(900, "9000", "데이터베이스 제약조건 위배(duplicated service solution id)"),


    BAD_REQUEST_EMPTY_TRANSACTION_ID(400, "4001", "transactionId가 비어 있습니다."),
    BAD_REQUEST_EMPTY_PROD_CODE(400, "4002", "카드 상품코드가 비어 있습니다."),
    BAD_REQUEST_EMPTY_CI(400, "4003", "CI가 비어 있습니다."),
    BAD_REQUEST_EMPTY_MDN(400, "4004", "MDN이 비어 있습니다."),
    BAD_REQUEST_EMPTY_CORP_CODE(400, "4010", "제휴사 코드가 비어 있습니다."),
    BAD_REQUEST_EMPTY_EVENT_ID(400, "4022", "eventId가 비어 있습니다."),
    BAD_REQUEST_INVALID_PROD_CODE(400, "4006", "유효하지 않은 prodCode 입니다."),
    BAD_REQUEST_INVALID_CORP_CODE(400, "4007", "유효하지 않은 corpCode 입니다."),

    MEMBER_EMPTY_PARTNER_MEMBER(900, "9001", "고객사 회원 정보가 존재하지 않습니다"),
    BAD_REQUEST_INVALID_REQUEST_BODY(900, "9100", "INVALID REQUEST BODY"),

    // IP ACCESS 관련
    IP_ACCESS_ERROR_SYRUPSTORE(900, "9800", "접근할 수 없는 IP ADDRESS 입니다."),
    IP_ACCESS_ERROR_IA(900, "9801", "접근할 수 없는 IP ADDRESS 입니다."),

    ENCRYPTION_ERROR(900, "9820", "encryption error"),
    DECRYPTION_ERROR(900, "9821", "decryption error"),

    // 공통 오류
    UNKNOWN_ERROR(900, "9999", "서버 오류");

    private int httpStatusCode; // http code, res:id
    private String code; // result code, res:code
    private String message; // message

    ErrorCode(int httpStatusCode, String code, String message) {
        this.httpStatusCode = httpStatusCode;
        this.code = code;
        this.message = message;
    }

}
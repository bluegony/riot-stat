package com.web.exception.custom;

import com.web.exception.enu.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

	private int httpStatusCode;
	private String code;
	private String message;
	private String detailMessage;
	private Object data;
	private ErrorCode errorCode;
	
	public CustomException(int httpStatusCode, String code, String message) {
		super(message);
		this.httpStatusCode = httpStatusCode;
		this.code = code;
		this.message = message;
	}

	public CustomException(ErrorCode e) {
		super(e.getMessage());
		this.errorCode = e;
		this.httpStatusCode = e.getHttpStatusCode();
		this.code = e.getCode();
		this.message = e.getMessage();
	}

	public CustomException(ErrorCode e, String detailMessage) {
		super(e.getMessage());
		this.errorCode = e;
		this.httpStatusCode = e.getHttpStatusCode();
		this.code = e.getCode();
		this.message = e.getMessage();
		this.detailMessage = detailMessage;
	}

	public CustomException(ErrorCode e, Object data) {
		super(e.getMessage());
		this.errorCode = e;
		this.data = data;
		this.httpStatusCode = e.getHttpStatusCode();
		this.code = e.getCode();
		this.message = e.getMessage();
	}

	public CustomException(ErrorCode e, String detailMessage, Object data) {
		super(e.getMessage());
		this.errorCode = e;
		this.data = data;
		this.httpStatusCode = e.getHttpStatusCode();
		this.code = e.getCode();
		this.message = e.getMessage();
		this.detailMessage = detailMessage;
	}

}

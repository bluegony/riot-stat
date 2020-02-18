package com.web.exception;

/**
 * Created by 1000773 on 2017. 6. 8..
 */

import com.web.exception.custom.CustomException;
import com.web.exception.custom.SimpleException;
import com.web.exception.dto.ErrorResponse;
import com.web.exception.enu.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.time.DateTimeException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler  {

//    @Autowired
//    ApiLogService apiLogService;

    private static final String STANDARD_SPRING_EXCEPTION = "STANDARD_SPRING_EXCEPTION";

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        log.error(STANDARD_SPRING_EXCEPTION, e);
//        return super.handleExceptionInternal(e, body, headers, status, request);
//    }

    /**
     * makeErrorResponse : api_log table update하고 ErrorResponse 만들어서 리턴
     * @param response
     * @param e
     * @return
     */
    private ErrorResponse makeErrorResponse(HttpServletResponse response, ErrorCode e, Object errorData) {
        int httpStatus = e.getHttpStatusCode();

//        Map<String,Object> errorObject = new HashMap<>();
//        errorObject.put(errorData.getClass().getSimpleName(), errorData);
        ErrorResponse errorResponse = new ErrorResponse(e, errorData);
//        apiLogService.updateApiLog(errorResponse, httpStatus);
        response.setStatus(httpStatus);
        return errorResponse;
    }
    private ErrorResponse makeErrorResponse(HttpServletResponse response, ErrorCode e, String detailMessage, Object errorData) {
        int httpStatus = e.getHttpStatusCode();

//        Map<String,Object> errorObject = new HashMap<>();
//        errorObject.put(errorData.getClass().getSimpleName(), errorData);
        ErrorResponse errorResponse = new ErrorResponse(e, detailMessage, errorData);
//        apiLogService.updateApiLog(errorResponse, httpStatus);
        response.setStatus(httpStatus);
        return errorResponse;
    }
    private ErrorResponse makeErrorResponse(HttpServletResponse response, ErrorCode e) {
        return makeErrorResponse(response, e, null);
    }


    /**
     * SimpleException : 에러 원인이 간단한 경우 throw하는 부분에서 stack trace를 찍을 필요 없이 여기서만 찍는다.
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(SimpleException.class)
    @ResponseBody
    public ErrorResponse handle(HttpServletResponse response, SimpleException e) {
        log.error(e.getMessage(), e);
        if(e.getData()!=null) {

//            log.info(StringTools.objectToJson(e.getData()), false);
            log.info("------------------------- GlobalExceptionHandler::HandleSimpleException ----------------------------");
//            errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getData());
        } else {
//            errorResponse = new ErrorResponse(e.getCode(), e.getMessage());
        }

        return makeErrorResponse(response, e.getErrorCode(), e.getData());
    }

    /**
     * CustomException : 내부 문제로 에러가 발생한 경우. throw하는 부분에서 stack trace log를 찍고 여기서는 안찍는다.
     * try catch 한 exception을 변환해서 보냈을 경우, 여기서 stack trace 찍는건 의미가 없음
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ErrorResponse handle(HttpServletResponse response, CustomException e) {
        if(e.getData()!=null) {
//            log.info(StringTools.objectToJson(e.getData()), false);
            log.info("------------------------- GlobalExceptionHandler::HandleCustomException ----------------------------");
//            errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getData());
        } else {
//            errorResponse = new ErrorResponse(e.getCode(), e.getMessage());
        }

        return makeErrorResponse(response, e.getErrorCode(), e.getDetailMessage(), e.getData());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handle(HttpServletResponse response, DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);

        ErrorCode ee = ErrorCode.BAD_REQUEST_CONSTRAINT_VIOLATION;
        return makeErrorResponse(response, ee);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ErrorResponse handle(HttpServletResponse response, NoHandlerFoundException e) {
        log.info(e.getMessage(), e);

        ErrorCode ee = ErrorCode.PAGE_NOT_FOUND;
        return makeErrorResponse(response, ee);
    }


    /**
     * Bad Request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorResponse handle(HttpServletResponse response, HttpMessageNotReadableException e) {
        log.info(e.getMessage(),e);
        ErrorCode ee = ErrorCode.BAD_REQUEST_INVALID_REQUEST_BODY;
        return makeErrorResponse(response, ee);
    }


    /**
     * @Valid Error
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(e.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), errorMsg);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handle(HttpServletResponse response, RuntimeException e) {
        log.error(e.getMessage(), e);
        ErrorCode ee = ErrorCode.UNKNOWN_ERROR;
        return makeErrorResponse(response, ee);
    }




}
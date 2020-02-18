package com.web.requestprocessing;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.exception.custom.CustomException;
import com.web.exception.enu.ErrorCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Getter
@ToString
public abstract class CommonRequestProcess {

    protected boolean checkCorpCode = false;
    protected boolean checkProdCode = false;
    protected boolean checkSwMemberId = false;
    protected boolean checkCi = false;
    protected boolean checkMdn = false;

    private String traceNo;
    protected String corpCode;
    protected String prodCode;
    protected String swMemberId;
    protected String swCardId;
    protected String ci;
    protected String mdn;

    @JsonIgnore
    protected ServiceHolder serviceHolder;

    public abstract CommResponse2 process();

    public abstract void setupValidation();

    public Object processRequest(HttpServletRequest request,  ServiceHolder serviceHolder) {
        this.serviceHolder = serviceHolder;
        log.debug(this.toString());
        String apiResponse = null;
        CommResponse2 response = null;
        try {
            validateParameters();
            response = process();
        } catch (CustomException ex) {
//            ex.printStackTrace();
            apiResponse = ex.toString();
            throw ex;
        } finally {
            ObjectMapper mapper = new ObjectMapper();
            try {
                if (null != response) {
                    response.setTraceNo(traceNo);
                }
                if(StringUtils.isEmpty(apiResponse)) {
                    apiResponse = mapper.writeValueAsString(response);
                }
//                String apiLogCi = StringUtils.isNotBlank(ci)?ci:serviceHolder.memberService.getCiBySwMember(swMemberId);
//                serviceHolder.apiLogService.insertApiLog(
//                        new ApiLog(
//                                request.getRequestURI(),
//                                ProdCode.getProdCodeByCode(prodCode).getCorpCodeString(),
//                                prodCode,
//                                apiLogCi,
//                                mapper.writeValueAsString(this),
//                                apiResponse)
//                );
            } catch (JsonProcessingException e) {
                log.error("exception!", e);
            }
        }
        return apiResponse;
    }

    public void validateParameters() {
        setupValidation();
        log.debug("{} {} {} {} {}", checkCi, checkMdn, checkProdCode, checkSwMemberId, checkCorpCode);
        if (checkCi && StringUtils.isEmpty(ci)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_EMPTY_CI);
        }
        if (checkMdn && StringUtils.isEmpty(mdn)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_EMPTY_MDN);
        }
        if (checkProdCode && StringUtils.isEmpty(prodCode)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_INVALID_PROD_CODE);
        }
    }


    protected static CommResponse2 getCommResponse2(Object resData, String resultCode) {
        log.debug(resData.toString());

        if (resultCode.equals("0000") || resultCode.equals("0001")) { // 정상, 기처리건
            return new CommResponse2().setResData(resData);
        } else if (resultCode.equals("5580")) {
            throw new CustomException(ErrorCode.MEMBER_EMPTY_PARTNER_MEMBER);
        } else {
            throw new CustomException(ErrorCode.UNKNOWN_ERROR);
        }
    }

}

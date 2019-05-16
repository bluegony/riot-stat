package com.skplanet.solution.modules.purchase.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by 1000773
 */
@Setter
@Getter
@Builder
public class Purchase {
    
    @ApiModelProperty(value = "승인 결제 TR primary id")
    private Long id; //

    @ApiModelProperty(value = "배치 history id")
    private Long batchHistoryId; //

    @ApiModelProperty(value = "데이터 인입 타입 (B:batch, R:realTime, A:all")
    private String dataInType; // 데이터 인입타입

    @ApiModelProperty(value = " 하나카드 상품코드")
    private String partnerCardCode;

    @ApiModelProperty(value = " 카드번호 (16CHAR)")
    private String cardNo;

    @ApiModelProperty(value = " 승인번호 (8CHAR)")
    private String approvalNumber;

    @ApiModelProperty(value = " 승인일자(YYYYMMDD) (8byte)")
    private String approvalDate;

    @ApiModelProperty(value = " 승인시각(HHmmss) (6byte)")
    private String approvalTime;
    
    @ApiModelProperty(value = " 매입일자(YYYYMMDD) (8byte)")
    private String purchaseDate;
    
    @ApiModelProperty(value = " 금융사코드 (6byte)")
    private String corpCode;

    @ApiModelProperty(value = " 제휴사카드코드 (4byte)")
    private String prodCode;

    @ApiModelProperty(value = " 해외거래여부 (1CHAR)")
    private String abroadUse;

    @ApiModelProperty(value = " 미승인 매입 여부 (1CHAR)")
    private String noApprovalPurchase;

    @ApiModelProperty(value = " 매입 금액 (14CHAR)")
    private BigDecimal payAmount;

    @ApiModelProperty(value = " 가맹점 업종 코드 (4byte)")
    private String merchantIndustryCode;

    @ApiModelProperty(value = " 가맹점 사업자번호 (10 CHAR)")
    private String merchantBizNo;

    @ApiModelProperty(value = " 가맹점 구분코드 (2 CHAR) (HA, BC 등)")
    private String merchantTypeCode;

    @ApiModelProperty(value = " 가맹점 번호 (16 CHAR)")
    private String merchantNo;

    @ApiModelProperty(value = " 가맹점명 (50 CHAR)")
    private String merchantName;

    @ApiModelProperty(value = " 거래형태 [1byte, (A:승인, C:취소)]")
    private String trType;

    @ApiModelProperty(value = "등록일")
    private LocalDateTime createdDate; // 등록일

    @ApiModelProperty(value = "수정일")
    private LocalDateTime lastModifiedDate; // 수정일
}

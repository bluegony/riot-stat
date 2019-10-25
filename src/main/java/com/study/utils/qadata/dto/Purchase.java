package com.study.utils.qadata.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created
 */
@Setter
@Getter
@Builder
public class Purchase {

    private Long id; //

    private Long batchHistoryId; //

    private String dataInType; // 데이터 인입타입

    private String partnerCardCode;

    private String cardNo;

    private String approvalNumber;

    private String approvalDate;

    private String approvalTime;

    private String purchaseDate;

    private String corpCode;

    private String prodCode;

    private String abroadUse;

    private String noApprovalPurchase;

    private BigDecimal payAmount;

    private String merchantIndustryCode;

    private String merchantBizNo;

    private String merchantTypeCode;

    private String merchantNo;

    private String merchantName;

    private String trType;

    private LocalDateTime createdDate; // 등록일

    private LocalDateTime lastModifiedDate; // 수정일
}

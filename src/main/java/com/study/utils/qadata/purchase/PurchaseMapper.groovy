package com.skplanet.solution.modules.purchase

import com.skplanet.solution.infra.mybatis.Mapper
import com.skplanet.solution.modules.purchase.dto.Purchase
import org.apache.ibatis.annotations.Insert

/**
 * Created by 1000773 on 2018. 10. 2..
 */
@Mapper
interface PurchaseMapper {

    @Insert("""<script>
        MERGE INTO CLO_PURCHASE
        USING DUAL
        ON (
          CORP_CODE = #{corpCode}
          AND PROD_CODE = #{prodCode}
          AND CARD_NO = #{cardNo}
          AND APPROVAL_NUMBER = #{approvalNumber}
          AND APPROVAL_DATE = #{approvalDate}
          AND APPROVAL_TIME = #{approvalTime}
          AND PAY_AMOUNT = #{payAmount}
          AND TR_TYPE = #{trType}
        )
        WHEN MATCHED THEN
          UPDATE SET
            LAST_MODIFIED_DATE = SYSDATE
        WHEN NOT MATCHED THEN
          INSERT (
            ID, BATCH_HISTORY_ID, DATA_IN_TYPE, PARTNER_CARD_CODE,
            CORP_CODE, PROD_CODE, CARD_NO,
            APPROVAL_NUMBER, APPROVAL_DATE, APPROVAL_TIME, PURCHASE_DATE,
            ABROAD_USE, NO_APPROVAL_PURCHASE,
            PAY_AMOUNT, 
            MERCHANT_INDUSTRY_CODE, MERCHANT_BIZ_NO, MERCHANT_TYPE_CODE, MERCHANT_NO,  MERCHANT_NAME, 
            TR_TYPE, 
            CREATED_DATE
          )
          VALUES(
            SEQCLOPURCHASE.NEXTVAL, #{batchHistoryId,jdbcType=VARCHAR}, #{dataInType,jdbcType=VARCHAR}, #{partnerCardCode},
            #{corpCode}, #{prodCode}, #{cardNo},
            #{approvalNumber}, #{approvalDate}, #{approvalTime}, #{purchaseDate}, 
            #{abroadUse}, #{noApprovalPurchase},
            #{payAmount},
            #{merchantIndustryCode}, #{merchantBizNo}, #{merchantTypeCode}, #{merchantNo}, #{merchantName}, 
            #{trType},
            SYSDATE
          )
    </script>""")
    void merge(Purchase purchase)

    @Insert("""<script>
        INSERT INTO CLO_PURCHASE (
            ID, BATCH_HISTORY_ID, DATA_IN_TYPE, PARTNER_CARD_CODE,
            CORP_CODE, PROD_CODE, CARD_NO,
            APPROVAL_NUMBER, APPROVAL_DATE, APPROVAL_TIME, PURCHASE_DATE,
            ABROAD_USE, NO_APPROVAL_PURCHASE,
            PAY_AMOUNT, 
            MERCHANT_INDUSTRY_CODE, MERCHANT_BIZ_NO, MERCHANT_TYPE_CODE, MERCHANT_NO,  MERCHANT_NAME, 
            TR_TYPE, 
            CREATED_DATE
          )
          VALUES (
            SEQCLOPURCHASE.NEXTVAL, #{batchHistoryId,jdbcType=VARCHAR}, #{dataInType,jdbcType=VARCHAR}, #{partnerCardCode},
            #{corpCode}, #{prodCode}, #{cardNo},
            #{approvalNumber}, #{approvalDate}, #{approvalTime}, #{purchaseDate}, 
            #{abroadUse}, #{noApprovalPurchase},
            #{payAmount},
            #{merchantIndustryCode}, #{merchantBizNo}, #{merchantTypeCode}, #{merchantNo}, #{merchantName}, 
            #{trType},
            SYSDATE
          )
    </script>""")
    Long insert(Purchase purchase)
}
package com.web.dto;

import com.web.requestprocessing.CommResponse2;
import com.web.requestprocessing.CommonRequestProcess;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@ToString(callSuper = true)
public class CardIssueProcessRequest extends CommonRequestProcess {

    @Override
    public void setupValidation() {
        checkCi = true;
        checkProdCode = true;
    }
    @Override
    public CommResponse2 process() {
//        TrObject tr = serviceHolder.checkMembershipLocalService.sendMemberCardIssueProcessRequest(prodCode, ci);
        TrObject tr = new TrObject();

        Map resData = new HashMap<>();
        resData.put("resultCode", tr.resultCode());
        resData.put("issueStatus", tr.getIssueStatus());
        return getCommResponse2(resData, tr.resultCode());
    }

}

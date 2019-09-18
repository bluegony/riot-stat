package com.study.dto.request_response_json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skplanet.pfms.api.dto.suggestionCode.dto.RequestCommonDto;
import com.skplanet.pfms.api.service.ServiceHolder;
import com.skplanet.pfms.common.dto.CommonResponse;
import com.skplanet.pfms.commonLogic.configadmin.entity.ConfigSuggestionCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by 1000773 on 2019. 1. 11..
 */
@Getter
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuggestionCodeListRequest extends RequestCommonDto {

//    private SuggestionCodeListDto reqData;

    @Getter
    static class SuggestionCodeListDto  {
        private String isUsing;      // 활성화 상태
        private int size = 10;   // 페이지당 사이즈 default 10
        private int page = 1;   // 페이지 번호 default 1
        private Long id;        // event id로 조회

    }



    @Override
    public Object processRequest(ServiceHolder serviceHolder) {
//        String reqBody = JsonUtils.objectToJson(getReqBody());
//        log.info(reqBody);
//        SuggestionCodeListDto reqData = JsonUtils.objectToClass(getReqBody(), SuggestionCodeListDto.class);
        SuggestionCodeListDto reqData = getReqBody(SuggestionCodeListDto.class);

        int page = Math.max(reqData.page-1, 0);                 // 잘못된 값이 들어오면 1페이지를 전달
        int size = reqData.size<1?10:reqData.size;              // 잘못된 값이 들어오면 10 항목 전달

        log.info("input:{},{} using:{},{}", reqData.page, reqData.size, page, size);
        List<ConfigSuggestionCode> list = serviceHolder.getSuggestionCodeRepository().findAll(new PageRequest(page, size,  Sort.Direction.ASC,"configSuggestionCodeId")).getContent();

        return new CommonResponse(list);
    }

//    private CommResponse2 getListAll(ServiceHolder serviceHolder) {
//        TargetEventMapper targetEventMapper = serviceHolder.getTargetEventMapper();
//
//        String requestStatus = reqData.getStatus();
//        List<TargetEvent> allEventList = targetEventMapper.searchList(reqData);
//        List<TargetEvent> result = new ArrayList<>();
//        int index = 0;
//        for(TargetEvent e: allEventList) {
//            if(e.getStatus().equals(temp.getCode())) continue;
//            if (e.getStatus().equals(approved.getCode())) {
//                LocalDateTime now = LocalDateTime.now();
//                LocalDateTime startDate = LocalDateTime.parse(e.getStartDt(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//                LocalDateTime endDate = LocalDateTime.parse(e.getEndDt(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//                if (now.isAfter(endDate)) {
//                    e.setStatus(terminated_by_time.getCode());
//                } else {
//                    if (now.isAfter(startDate) || now.isEqual(startDate)) {
//                        e.setStatus(on_service.getCode());
//                    }
//                }
//            }
//            if (StringUtils.isBlank(requestStatus) || e.getStatus().equals(requestStatus)) {
//                index++;
//                int page = reqData.getPage();
//                int size = reqData.getSize();
//                if((page-1)*size<index && index<=(page-1)*size+size) {
////                    log.debug("add {}", e);
//                    result.add(e);
//                }
//            }
//        }
////        log.debug(StringTools.objectToJson(new ListAndCountDto(index, result)));
//        return new CommResponse2().setReqData(reqData).setResData(new ListAndCountDto(index, result));
//    }

}

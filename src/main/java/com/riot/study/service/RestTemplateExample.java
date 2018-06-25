//package com.riot.study.service;
//
//import com.riot.study.utils.StringTools;
//import com.skplanet.solution.batch.modules.batch.dto.BatchResult;
//import com.skplanet.solution.infra.crypto.OcbCryptoUtils;
//import com.skplanet.solution.modules.common.enu.CorpCode;
//import com.skplanet.solution.modules.common.enu.ProdCode;
//import com.skplanet.solution.modules.rewardPoint.RewardPointMapper;
//import com.skplanet.solution.modules.rewardPoint.dto.AvailablePoint;
//import com.skplanet.solution.modules.rewardPoint.dto.RewardPointUse;
//import com.skplanet.solution.modules.rewardPoint.enu.PointUseStatus;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpStatusCodeException;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Base64;
//import java.util.List;
//
///**
// * Created on 2017. 4. 20..
// */
//@Slf4j
//@Service
//public class RestTemplateExample {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Value("${ocbpay.url}")
//    private String ocbUrl;
//    @Value("${ocbpay.username}")
//    private String username;
//    @Value("${ocbpay.key}")
//    private String key;
//
//    private static String ocbPath = "/payui/api/pay/save/v1.0/point";
//
//
//
//    /**
//     * consume restTemplate example
//     */
//    public void sendOcbPoint() {
//        String data = "{\t\n" +
//                "\"mctTransactionId\":\"20180621asdf123\",\n" +
//                "\"nxMctNo\":\"70012515\",\n" +
//                "\"nxMctCorpNo\":\"1048656659\",\n" +
//                "\"authIdType\":\"CI\",\n" +
//                "\"authId\":\"5ZqbfUT05cgsQ6936z7xhpgLE2GZxRJx7pPXwQM4pf50lUsGHeY8hfrB+NN4Y4t+HwSKGZBmMKOy96xDbSndog==\",\n" +
//                "\"requestOcbPoint\":12345\n" +
//                "\n" +
//                "\n" +
//                "}";
//
//        HttpHeaders headers = createHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
////        HttpEntity param= new HttpEntity(JSONInput, headers);
//
//
//        HttpEntity<String> request = new HttpEntity<String>( OcbCryptoUtils.encrypt( key, data), headers);
////        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
////        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        try {
//            String response = restTemplate.postForObject(ocbUrl + ocbPath, request, String.class);
//        }
//        catch(HttpStatusCodeException errorException) {
//            String responseBody = errorException.getResponseBodyAsString();
//            // You can use this string to create MyOutput pojo using ObjectMapper.
//            log.info("response body:{}",OcbCryptoUtils.decrypt(key, responseBody), errorException);
//        }
////        log.info(response);
//
//
//
//    }
//
//
//    @Test
//    public void testSendOcbPaySave() throws Exception {
////        rewardPointService.sendOcbPoint(null,null);
//        OcbPaySave ocbPaySave = new OcbPaySave("70012515", "1048656659", "5ZqbfUT05cgsQ6936z7xhpgLE2GZxRJx7pPXwQM4pf50lUsGHeY8hfrB+NN4Y4t+HwSKGZBmMKOy96xDbSndog==", 12345 );
//        rewardPointService.sendOcbPaySave(ocbPaySave, 0L, ProdCode.HANA_1QCOUPON_CREDIT);
//    }
//
//    public BatchResult sendOcbPoint(Long batchHistoryInfoId, ProdCode prodCode) {
//        List<RewardPointUse> list = rewardPointMapper.selectExpiredRewardPoint(prodCode.getCode());
//        BatchResult batchResult = new BatchResult();
//        batchResult.setTargetCount(list.size());
//        int errorCount = 0;
//        int successCount = 0;
//        for(RewardPointUse r : list) {
//            log.info(r.toString());
//            try {
////                OcbPaySave ocbPaySave = new OcbPaySave(nxMctNo, nxMctCorpNo, "5ZqbfUT05cgsQ6936z7xhpgLE2GZxRJx7pPXwQM4pf50lUsGHeY8hfrB+NN4Y4t+HwSKGZBmMKOy96xDbSndog==", r.getUseAmount());
//                OcbPaySave ocbPaySave = new OcbPaySave(nxMctNo, nxMctCorpNo, r.getCi(), r.getUseAmount());
//                sendOcbPaySave(ocbPaySave, batchHistoryInfoId, prodCode);
//
//                r.setBatchHistoryId(batchHistoryInfoId);
//                r.setUseStatus(PointUseStatus.OCB_SEND_SUCCESS.getCode());
//                rewardPointMapper.updateRewardPointUse(r);
//                successCount++;
//            } catch(Exception e) {
//                log.warn("sendOcbPoint error!", e);
//                errorCount++;
//            }
//        }
//
//        log.warn("error count = {}", errorCount);
//        batchResult.add(successCount, errorCount);
//        return batchResult;
//    }
//
//    public void sendOcbPaySave(OcbPaySave ocbPaySave, Long batchHistoryInfoId, ProdCode prodCode) throws Exception {
//        String data = StringTools.parseToJson(ocbPaySave);
//        log.info(data);
//
//        HttpHeaders headers = createHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
//        HttpEntity<String> request = new HttpEntity<String>( OcbCryptoUtils.encrypt( key, data), headers);
//        String responseBody="";
//        boolean success = false;
//        try {
//            responseBody = restTemplate.postForObject(ocbUrl + ocbPath, request, String.class);
//            success = true;
//            log.info("\nresponse body : {}",OcbCryptoUtils.decrypt(key, responseBody));
//        }
//        catch(HttpStatusCodeException errorException) {
//            responseBody = errorException.getResponseBodyAsString();
//            log.info("\nresponse body : {}",OcbCryptoUtils.decrypt(key, responseBody), errorException);
//            throw errorException;
//        } finally {
//            RewardPointOutLog rewardPointOutLog = new RewardPointOutLog(ocbPaySave, prodCode.getCode(), data, OcbCryptoUtils.decrypt(key, responseBody), success, batchHistoryInfoId);
//            rewardPointMapper.insertRewardPointOutTransaction(rewardPointOutLog);
//        }
//    }
//
//    private HttpHeaders createHeaders(){
//        return new HttpHeaders() {{
//            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//            String encodedKey = OcbCryptoUtils.encrypt( key,username+","+time );
//            String auth = username + ":" + encodedKey;
//            Base64.Encoder b64Encoder = Base64.getEncoder();
//            byte[] encodedAuth = b64Encoder.encode(auth.getBytes());
////            byte[] encodedAuth = Base64.encodeBase64(
////                    auth.getBytes(Charset.forName("US-ASCII")) );
//            String authHeader = "Basic " + new String( encodedAuth );
////            log.debug(authHeader.toString());
//            set( "Authorization", authHeader );
//        }};
//    }
//
//
//
//
//}

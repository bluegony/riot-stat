//package com.study.utils;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Slf4j
//public class RequestUtil {
//
//    public static String sendHttpRequest(HttpUriRequest req, Map<String,String> headersMap) throws IOException {
//
//        if(headersMap!=null) {
//            for (Map.Entry<String, String> elem : headersMap.entrySet()) {
//                req.addHeader(elem.getKey(), elem.getValue());
//            }
//        }
////        for(Header h : req.getAllHeaders()) {
////            log.debug("header name : {}, value : {}",h.getName(),h.getValue());
////        }
////        System.setProperty("jsse.enableSNIExtension", "false");
//
//        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
//
////            log.info("set proxy");
////            HttpHost proxy = new HttpHost("localhost", 8888);
////            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
////            ((HttpPost)req).setConfig(config);
//
//            log.debug(req.toString());
//            try (CloseableHttpResponse response = httpclient.execute(req)) {
//                if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK) {
//                    log.info("code:"+response.getStatusLine().getStatusCode());
//                    log.warn("HttpStatus is NOT OK : \n" + response.toString());
////                    throw new RuntimeException(" server error " );
//                }
//                log.debug(response.toString());
//
//                return EntityUtils.toString(response.getEntity());
//            }
//        }
//    }
//
//
//
//    public static String sendHttpPostRequestWithJson(String requestUrl, Object paramObject, Map<String, String> headersMap) throws IOException {
//
//        log.debug("postRequest url : "+requestUrl+"\nheader : "+headersMap+"\nbody : "+paramObject);
//
//        HttpPost httppost = new HttpPost(requestUrl);
//        String param = new ObjectMapper().writeValueAsString(paramObject);
//        httppost.setEntity(new StringEntity(param, ContentType.APPLICATION_JSON));
//        log.debug(param);
//
//        return sendHttpRequest(httppost, headersMap);
//    }
//
//    public static String sendHttpGetRequest(String requestUrl, Map<String,String> headersMap) throws IOException {
//
//        log.info("send request : "+ requestUrl);
//
//        HttpGet httpget = new HttpGet(requestUrl);
//
//        return sendHttpRequest(httpget, headersMap);
//    }
//
//
//}
//package com.study.utils;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpStatus;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.protocol.HttpContext;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Map;
//
//
///**
// * Created on 15. 7. 28..
// */
//@Slf4j
//@Service
//public class RequestService {
//
//
//    @Autowired
//    private ProxyConfig _proxyConfig;
//    private Map<String,String> _headersMap;
//    private boolean _useProxy;
//
//    public String postWithJson(String requestUrl, Object paramObject) throws IOException {
//        return postWithJson(requestUrl, paramObject, null, false);
//    }
//    public String postWithJson(String requestUrl, Object paramObject, Map<String, String> headersMap, boolean useProxy) throws IOException {
////        _headersMap = headersMap;
////        _useProxy = useProxy;
//        log.debug("postRequest url : "+requestUrl+"\nheader : "+headersMap+"\nbody : "+StringTools.objectToJson(paramObject));
//        HttpPost httppost = new HttpPost(requestUrl);
//        String param = new ObjectMapper().writeValueAsString(paramObject);
//        httppost.setEntity(new StringEntity(param, ContentType.APPLICATION_JSON));
////        log.debug(param);
//        return sendHttpRequest(httppost, headersMap, useProxy);
//    }
//
//
//    public String get(String requestUrl) throws IOException {
//        return get(requestUrl, null, false);
//    }
//    public String get(String requestUrl, boolean useProxy) throws IOException {
//        return get(requestUrl, null, useProxy);
//    }
//    public String get(String requestUrl, Map<String,String> headersMap, boolean useProxy) throws IOException {
////        _headersMap = headersMap;
////        _useProxy = useProxy;
//        log.debug("send request : "+ requestUrl);
//        HttpGet httpget = new HttpGet(requestUrl);
//        return sendHttpRequest(httpget, headersMap, useProxy);
//    }
//
//
//    public String sendHttpRequest(HttpRequestBase req, boolean useProxy) throws IOException {
//        return sendHttpRequest(req, null, useProxy);
//    }
//    public String sendHttpRequest(HttpRequestBase req, Map<String,String> headersMap, boolean useProxy) throws IOException {
//        _headersMap = headersMap;
//        _useProxy = useProxy;
//        return sendHttpRequest(req);
//    }
//
//    private String sendHttpRequest(HttpRequestBase req) throws IOException {
//
//        if(_headersMap!=null) {
//            for (Map.Entry<String, String> elem : _headersMap.entrySet()) {
//                req.addHeader(elem.getKey(), elem.getValue());
//            }
//        }
//
//        CredentialsProvider credsProvider = null;
//        if(_proxyConfig!=null && _useProxy==true) {
//            credsProvider = new BasicCredentialsProvider();
//            credsProvider.setCredentials(
//                    new AuthScope(_proxyConfig.getProxyHost(), _proxyConfig.getProxyPort()),
//                    new UsernamePasswordCredentials(_proxyConfig.getProxyUser(), _proxyConfig.getProxyPassword()));
//        }
//
//        try ( CloseableHttpClient httpclient = HttpClients.custom()
//                .setDefaultCredentialsProvider(credsProvider).build() ) {
//
//            HttpContext httpContext = null;
//            if(_proxyConfig!=null && _useProxy==true) {
//                log.info("set proxy : {}", _proxyConfig.toString());
//                HttpHost proxy = new HttpHost(_proxyConfig.getProxyHost(), Integer.valueOf(_proxyConfig.getProxyPort()));
//                RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//                req.setConfig(config);
//
//            }
//
//            log.info(req.toString());
//            try (CloseableHttpResponse response = httpclient.execute(req, httpContext)) {
//                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
//                    log.info("code:"+response.getStatusLine().getStatusCode());
//                    log.warn("HttpStatus is NOT OK : \n" + response.toString());
//                }
//
//                String responseString = EntityUtils.toString(response.getEntity());
//                return responseString;
//            }
//        }
//    }
//
//
//    public void sendHttpPostWithFormdataExample() {
//
////        String batch = StringTools.objectToJson(facebookBatchRequestList);
////        log.info(batch);
//
//        HttpEntity entity = MultipartEntityBuilder
//                .create()
//                .addTextBody("access_token", "your access token")
//                .addTextBody("batch", "batch string")
//                .build();
//
//        HttpPost httpPost = new HttpPost("http://your.destination.com");
//
//        httpPost.setEntity(entity);
//        try {
//            String response = sendHttpRequest(httpPost, true);
//            log.info(response);
//        } catch (IOException e) {
//            log.info("sendHttpRequest error",e);
//            return ;
//        }
//
//    }
//}

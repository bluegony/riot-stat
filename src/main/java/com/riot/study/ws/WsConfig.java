package com.riot.study.ws;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.HandshakeResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by 1000773 on 2018. 8. 13..
 */
@Slf4j
public class WsConfig extends ClientEndpointConfig.Configurator{

    @Override
    public void beforeRequest(Map<String, List<String>> headers) {
        final String API_KEY = "";
        final String API_SECRET = "";
        String nonce = Long.toString(System.currentTimeMillis());
        headers.put("api-nonce",  Arrays.asList(nonce));
        headers.put("api-signature",  Arrays.asList(generate_signature(API_SECRET, "GET", "/realtime", nonce, "")));
        headers.put("api-key",  Arrays.asList(API_KEY));
    }

    @Override
    public void afterResponse(HandshakeResponse hr) {
        Map<String, List<String>> headers = hr.getHeaders();
        log.info("headers -> "+headers);
    }


    private String generate_signature(String secret, String verb, String url, String nonce, String data) {

        try {
            String message = verb + url + nonce + data;
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            sha256_HMAC.init(secret_key);
            String hex = Hex.encodeHexString(sha256_HMAC.doFinal(message.getBytes()));
//            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
            System.out.println(hex);
            return hex;
        }
        catch (Exception e){
            log.info("generate_signature Error", e);
        }
        return null;
    }
}
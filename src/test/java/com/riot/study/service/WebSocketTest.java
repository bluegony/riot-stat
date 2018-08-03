//package com.riot.study.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.drafts.Draft_10;
//import org.java_websocket.handshake.ServerHandshake;
//import org.junit.Test;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.net.URI;
//import java.security.SecureRandom;
//import java.security.cert.X509Certificate;
//
///**
// * Created by 1000773 on 2018. 7. 2..
// */
//@Slf4j
//public class WebSocketTest {
//
//
//    @Test
//    public void testWebSocket() throws Exception {
////        SSLContext sslContext = SSLContext.getInstance("SSL");
////
////// set up a TrustManager that trusts everything
////        sslContext.init(null, new TrustManager[] { new X509TrustManager() {
////            public X509Certificate[] getAcceptedIssuers() {
////                System.out.println("getAcceptedIssuers =============");
////                return null;
////            }
////
////            public void checkClientTrusted(X509Certificate[] certs,
////                                           String authType) {
////                System.out.println("checkClientTrusted =============");
////            }
////
////            public void checkServerTrusted(X509Certificate[] certs,
////                                           String authType) {
////                System.out.println("checkServerTrusted =============");
////            }
////        } }, new SecureRandom());
//
//        WebSocketClient webSocketClient = new WebSocketClient(new URI("wss://testnet.bitmex.com"), new Draft_10()) {
//            @Override
//            public void onOpen(ServerHandshake serverHandshake) {
//                log.info("asdf1");
//                this.send("Hello");
//            }
//
//            @Override
//            public void onMessage(String message) {
//                log.info("asdf2");
//                if (message.equals("Hello")) {
//                    this.close();
//                } else {
//
//                    log.info("asdf3");
//                }
//
//            }
//
//            @Override
//            public void onClose(int code, String reason, boolean remote) {
//                log.info("asdf4 {} {} {}",code, reason, remote);
//            }
//
//            @Override
//            public void onError(Exception e) {
//                log.info("asdf", e);
//            }
//
//
//        };
//
//        try {
//            log.info("stsrt");
//            webSocketClient.connectBlocking();
//        } catch (Exception e) {
//            log.info("asdfasdf", e);
//        }
//    }
//}
//
//
//
//

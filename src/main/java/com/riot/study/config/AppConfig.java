package com.riot.study.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by bluegony on 2018. 6. 15..
 */
@Configuration
public class AppConfig {

//    final static int timeout = 1000 * 30;       // millisecond
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
//        return restTemplate;
//    }
//
//
//    private ClientHttpRequestFactory getClientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
//                = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setConnectTimeout(timeout);
//        clientHttpRequestFactory.setReadTimeout(timeout);
//        clientHttpRequestFactory.setConnectionRequestTimeout(timeout);
//        return clientHttpRequestFactory;
//    }

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        log.info("mappingJackson2HttpMessageConverter");
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        jsonConverter.setObjectMapper(objectMapper);
//        return jsonConverter;
//    }
}

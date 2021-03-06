package com.web.pgrservice;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

@Slf4j
@Getter
public class PgrLoginService {

    private Map<String,String> loginCookies;

    public void login() {
        if (loginCookies!=null) {
            log.info(loginCookies.toString());
            return ;
        }
        String id="bluegony";
        String pass="";
        if(StringUtils.isBlank(pass))
            throw new RuntimeException("password is blank");
        try {
            // login
            Connection.Response loginPageResponse = Jsoup.connect("https://pgr21.com/pb/login_check.php")
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36")
                    .header("Conetnt-Type", "application/x-www-form-urlencoded")
                    .method(Connection.Method.POST)
                    .data("user_id", id)
                    .data("password", pass)
                    .data("auto_login", "1")
                    .execute();
            Document loginPageDocument = loginPageResponse.parse();
            log.debug(loginPageDocument.toString());
            this.loginCookies = loginPageResponse.cookies();
        } catch (Exception e) {
            log.info("connect exception : ", e);
        }

    }
}
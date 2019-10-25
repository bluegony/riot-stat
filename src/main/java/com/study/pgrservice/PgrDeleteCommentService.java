package com.study.pgrservice;

import com.study.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * Created on 2019. 3. 6..
 */
@Service
@Slf4j
public class PgrDeleteCommentService {

    private String nickname;
    private String boardId;
    private Map<String,String> loginCooies;

    public void login(String boardId) {
        this.boardId = boardId;
        try {
            // login
            Connection.Response loginPageResponse = Jsoup.connect("https://pgr21.com/pb/login_check.php")
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36")
                    .header("Conetnt-Type", "application/x-www-form-urlencoded")
                    .method(Connection.Method.POST)
                    .data("user_id", "bluegony")
                    .data("password", "")
                    .data("auto_login", "1")
                    .execute();
            Document loginPageDocument = loginPageResponse.parse();
            loginCooies = loginPageResponse.cookies();
        } catch(Exception e) {
            log.info("connect exception : ",e);

        }

    }

    public void deleteAllCommentInBoard(String nickname, String boardId) {

        this.nickname = URLEncoder.encode(nickname);

        try {
            login(boardId);

            // start search
            String searchUrl = "https://pgr21.com/pb/pb.php?id="+boardId+"&sn=on&cmt=on&keyword="+this.nickname;
//            String searchUrl = "https://pgr21.com/pb/pb.php?id=free2&divpage=11&sn=on&keyword=Theia&cmt=on";

            while(true) {
                log.info("search : {}", searchUrl);
                Document boardSearch = Jsoup.connect(searchUrl).cookies(loginCooies).get();

//                log.info(boardSearch.toString());

                // get article list
                Elements articles = boardSearch.getElementsByClass("tdsub old");
                for(Element e:articles) {
                    String articleLink = "https://pgr21.com/pb/" + e.select("a[href]").get(0).attr("href");

                    // delete comments on each article
                    checkArticleAndDeleteComment(articleLink);
                }

                // next search
                if (searchUrl.contains("divpage=1&")) return;
                Elements pagination = boardSearch.getElementsByClass("pagination").get(0).select("a");
                List<Element> searchLinks = pagination.stream().filter(e -> StringUtils.isNotBlank(e.attr("href"))).collect(Collectors.toList());

                int size = searchLinks.size();
                int searchIndex = Math.min(1,size-1);
                searchUrl = "https://pgr21.com" + searchLinks.get(searchIndex).attr("href");
            }

        } catch(Exception e) {
            log.info("connect exception : ",e);

        }

//        Connection.Response response = Jsoup.connect("http://www.google.com")
//                .method(Connection.Method.GET)
//                .execute();
//        Document google3 = response.parse();
    }

    public void checkArticleAndDeleteComment(String url) throws Exception {

        log.info("enter article, url={}",url);
        String articleNo = url.split("&no")[1].split("&")[0];
        String commentNo;

        Document article = Jsoup.connect(url).cookies(loginCooies).get();
//        sleep(1000);
//        log.info(article.toString());
        Elements comments = article.getElementsByClass("cmemo myArticle");
        for(Element e: comments) {

            commentNo = e.parent().id().split("_")[1];
            log.info("\n1. article number = "+articleNo+", comment number = "+commentNo+", comment nickname = "+nickname);

            Element commentTree = article.getElementById("commentContainer_"+commentNo);
            if(commentTree==null || commentTree.children().size()==0) {

                deleteComment(articleNo, commentNo);

            } else {
                Elements elements = e.parent().select("a[href*='?b=']");
                for(Element ee:elements) {
                    log.info("check and modify comment :{}",ee.attr("href"));
                }
            }
        }
    }

    public void deleteComment(String articleNo, String commentNo) throws IOException {
        log.info("2.delete boardId:{} no:{} c_no:{}", boardId, articleNo, commentNo);
        Connection.Response deleteResponse = Jsoup.connect("https://pgr21.com/pb/del_comment_ok.php")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36")
                .header("Conetnt-Type", "application/x-www-form-urlencoded")
                .header("Referer", "https://pgr21.com/pb/pb.php?id=freedom&page=1&page_num=23&select_arrange=&desc=&sn=&keyword=&no=38752&cmt=&bpw=")
                .header("Origin", "https://pgr21.com")
                .method(Connection.Method.POST)
                .data("id", boardId)
                .data("no", articleNo)
                .data("c_no", commentNo)
                .data("confirm_del_text", "삭제합니다")
                .cookies(loginCooies)
                .execute();
//                log.info(deleteResponse.parse().toString());
    }
}

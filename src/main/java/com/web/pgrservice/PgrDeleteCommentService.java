package com.web.pgrservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
import java.util.stream.Collectors;


/**
 * Created on 2019. 3. 6..
 */
@Service
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class PgrDeleteCommentService {

    private PgrLoginService pgrLoginService;
    private String nickname;
//    private Map<String,String> loginCooies;

    public void deleteAllCommentByNickname(String nickName, List<String> boardId) {

        pgrLoginService = new PgrLoginService();
        pgrLoginService.login();

        for (String s : boardId) {
            log.info ("===== delete {} at {}", nickName, s);
            try {
                deleteAllCommentInBoard(nickName, s);
                sleep(300);
            } catch(Exception e) {
                log.info("connect exception : ",e);
                return;
            }
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            log.info("exception!", e);
        }
    }

    public void deleteAllCommentInBoard(String nickname, String boardId) throws Exception {

        this.nickname = URLEncoder.encode(nickname);


            // start search
            String searchUrl = "https://pgr21.com/pb/pb.php?id="+boardId+"&sn=on&cmt=on&keyword="+this.nickname;
//            String searchUrl = "https://pgr21.com/pb/pb.php?id=free2&divpage=11&sn=on&keyword=Theia&cmt=on";

            while(true) {
                log.info("search : {}", searchUrl);
                Document boardSearch = Jsoup.connect(searchUrl).cookies(pgrLoginService.getLoginCookies()).get();

//                log.info(boardSearch.toString());

                // get article list
                Elements articles = boardSearch.getElementsByClass("tdsub old");
                for(Element e:articles) {
                    String articleLink = "https://pgr21.com/" + e.select("a[href]").get(0).attr("href");

                    // delete comments on each article
                    checkArticleAndDeleteComment(boardId, articleLink);
                }

                // next search
                if (searchUrl.contains("divpage=1&")) return;
                Elements pagination = boardSearch.getElementsByClass("pagination").get(0).select("a");
                List<Element> searchLinks = pagination.stream().filter(e -> StringUtils.isNotBlank(e.attr("href"))).collect(Collectors.toList());

                int size = searchLinks.size();
                int searchIndex = Math.min(1,size-1);
                searchUrl = "https://pgr21.com" + searchLinks.get(searchIndex).attr("href");
                sleep(300);
            }

//        Connection.Response response = Jsoup.connect("http://www.google.com")
//                .method(Connection.Method.GET)
//                .execute();
//        Document google3 = response.parse();
    }

    public void checkArticleAndDeleteComment(String boardId, String url) throws Exception {

        log.info("enter article, url={}",url);
//        String articleNo = url.split("&no")[1].split("&")[0];
//        String[] a=url.split("\\?")[0].split("\\/");
//        for(int i=0; i<a.length; i++) {
//            log.info("index {}:{}", i , a[i]);
//        }
        String articleNo = url.split("\\?")[0].split("\\/")[5];
        String commentNo;

        Document article = Jsoup.connect(url).cookies(pgrLoginService.getLoginCookies()).get();
//        sleep(1000);
//        log.info(article.toString());
        Elements comments = article.getElementsByClass("cmemo myArticle");
        log.info("my comment count = {}", comments.size());
        for(Element e: comments) {

            commentNo = e.parent().id().split("_")[1];
            log.info("\n1. article number = "+articleNo+", comment number = "+commentNo+", comment nickname = "+nickname);

            Element commentTree = article.getElementById("commentContainer_"+commentNo);
            if(commentTree==null || commentTree.children().size()==0) {

                deleteComment(boardId, articleNo, commentNo);

            } else {
                Elements elements = e.parent().select("a[href*='?b=']");
                for(Element ee:elements) {
                    log.info("check and modify comment :{}",ee.attr("href"));
                }
            }
        }
    }

    public void deleteComment(String boardId, String articleNo, String commentNo) throws IOException {
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
                .cookies(pgrLoginService.getLoginCookies())
                .execute();
//                log.info(deleteResponse.parse().toString());
    }
}

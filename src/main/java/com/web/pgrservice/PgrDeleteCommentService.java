package com.web.pgrservice;

import lombok.Getter;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created on 2019. 3. 6..
 */
@Service
@Slf4j
@Getter
@Setter
public class PgrDeleteCommentService {

    private PgrLoginService pgrLoginService;
    private String nickname;
    private final int delayMilis = 0;

    public static void main(String args[]) throws IOException {
        PgrDeleteCommentService s = new PgrDeleteCommentService();
        s. run();
        return;
    }

    public void run() throws IOException {
        List<String> boards1 = Arrays.asList("freedom", "bulpan", "free2", "humor", "qna", "spoent", "gamenews", "election");
        List<String> oldBoard1 = Arrays.asList( "free","proposal","series","ace","daku","discuss","broadcasting","interview","event");
        List<String> oldBoard2 = Arrays.asList("ombudsman", "notice", "tournament" ,"starcraft2","war3","pds","valuation","translation","bug");

        // 결과 많은것(pgr21버그). -
        List<String> heavy = Arrays.asList("worldcup" ,"genius", "recommend","league","newvod", "olympic");

        String nickname = "구운아몬드";
        PgrDeleteCommentService s = new PgrDeleteCommentService();
        s.deleteAllCommentByNickname(nickname, oldBoard1);
        s.deleteAllCommentByNickname(nickname, oldBoard2);
        s.deleteAllCommentByNickname(nickname, boards1);
    }

    public void deleteAllCommentByNickname(String nickName, List<String> boardId) throws IOException {

        pgrLoginService = new PgrLoginService();
        pgrLoginService.login();

        for (String s : boardId) {
            log.info ("===== delete {} at {}", nickName, s);
            deleteAllCommentInBoard(nickName, s);
        }
    }

    public void deleteAllCommentInBoard(String nickname, String boardId) throws IOException {

        this.nickname = URLEncoder.encode(nickname);

        String searchUrl = "https://pgr21.com/pb/pb.php?id="+boardId+"&sn=on&cmt=on&keyword="+this.nickname;
        String searchPostMax=null;
        String searchPostMin=null;
        while(true) {
            log.info(" search : {}", searchUrl);
            Document boardSearch = Jsoup.connect(searchUrl).cookies(pgrLoginService.getLoginCookies()).get();
            log.debug(boardSearch.toString());

            Elements postNumbers = boardSearch.getElementsByClass("tdnum");
            int postCount = postNumbers.size();
            if(postCount>0) {
                String min = postNumbers.get(0).text();
                String max = postNumbers.get(postNumbers.size()-1).text();
                log.info("search result posts size={} min={} max={} oldMin={} oldMax={}", postNumbers.size(), min, max, searchPostMin, searchPostMax);
                if(min.equals(searchPostMin) && max.equals(searchPostMax)) {
                    log.info("same page, return;");
                    return;
                }
                else {
                    searchPostMin = min;
                    searchPostMax = max;
                }
            }

            // get article list
            Elements articles = boardSearch.getElementsByClass("tdsub old");
            for(Element e:articles) {
                String articleLink = "https://pgr21.com/" + e.select("a[href]").get(0).attr("href");

                // delete comments on each article
                checkArticleAndDeleteComment(boardId, articleLink);
            }

            // next search
            if (searchUrl.contains("divpage=1&")) {
                return;
            }
            Elements pagination = boardSearch.getElementsByClass("pagination").get(0).select("a");
            List<Element> searchLinks = pagination.stream().filter(e -> StringUtils.isNotBlank(e.attr("href"))).collect(Collectors.toList());

            int size = searchLinks.size();
            int searchIndex = Math.min(1,size-1);
            if(searchIndex==-1) {
                return;
            }
            searchUrl = "https://pgr21.com" + searchLinks.get(searchIndex).attr("href");
            sleep(300);
        }
    }

    public void checkArticleAndDeleteComment(String boardId, String url) throws IOException {

        log.info("enter article, url={}",url);
        String articleNo = url.split("\\?")[0].split("\\/")[5];
        String commentNo;

        Document article = Jsoup.connect(url).cookies(pgrLoginService.getLoginCookies()).get();
        Elements comments = article.getElementsByClass("cmemo myArticle");
//        log.debug(article.toString());
//        sleep(10000);
        int i=0,j=0;
        for(Element e: comments) {

            commentNo = e.parent().id().split("_")[1];
            log.info("    1. article number = "+articleNo+", comment number = "+commentNo+", comment nickname = "+nickname);

            Element commentTree = article.getElementById("commentContainer_"+commentNo);
            log.debug(commentTree.toString());
            sleep(delayMilis);

            if(e.text().equals("(수정됨) .")) {
                i++;
                // 이미 수정된것.
            } else {
                j++;
                // 삭제불가능한것도 있으므로 일단 수정.
                modifyComment(boardId, articleNo, commentNo);
                deleteComment(boardId, articleNo, commentNo);
            }
        }
        log.info(" my comment count = {}. already fixed={}, fixed or deleted now={}", comments.size(), i, j);
    }

    public void deleteComment(String boardId, String articleNo, String commentNo) throws IOException {
        log.info("    2.delete boardId:{} no:{} c_no:{}", boardId, articleNo, commentNo);
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


    public void modifyComment(String boardId, String articleNo, String commentNo) throws IOException {
        log.info("    2.modify boardId:{} no:{} c_no:{}", boardId, articleNo, commentNo);
        Connection.Response deleteResponse = Jsoup.connect("https://pgr21.com/pb/modify_comment_ok.php")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36")
                .header("Conetnt-Type", "application/x-www-form-urlencoded")
                .header("Referer", "https://pgr21.com/pb/pb.php?id=freedom&page=1&page_num=23&select_arrange=&desc=&sn=&keyword=&no=38752&cmt=&bpw=")
                .header("Origin", "https://pgr21.com")
                .method(Connection.Method.POST)
                .data("id", boardId)
                .data("no", articleNo)
                .data("c_no", commentNo)
                .data("memo", ".")
                .cookies(pgrLoginService.getLoginCookies())
                .execute();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            log.info("exception!", e);
        }
    }

}

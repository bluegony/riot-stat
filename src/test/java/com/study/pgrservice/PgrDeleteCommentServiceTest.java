package com.study.pgrservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019. 3. 6..
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PgrDeleteCommentService.class})
public class PgrDeleteCommentServiceTest {

    @Autowired
    PgrDeleteCommentService pgrDeleteCommentService;

    @Test
    public void deletePgrComment() throws Exception {


        pgrDeleteCommentService.login("free2");
//        String url = "https://pgr21.com/pb/pb.php?id=free2&no=63939&divpage=12&sn=on&ss=on&sc=on&keyword=Theia&cmt=on";
//        pgrDeleteCommentService.checkArticleAndDeleteComment(url);

//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "freedom");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "bulpan");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "free2");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "humor");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "qna");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "spoent");

        List<String> boards = Arrays.asList("freedom", "bulpan", "free2", "humor", "qna", "spoent");
        pgrDeleteCommentService.deleteAllCommentByNickname("Theia", boards);

//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "freedom");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "bulpan");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "free2");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "humor");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "qna");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "spoent");




//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "bulpan");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "genius");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "free");
//
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "recommend");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "gamenews");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "election");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "worldcup");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "olympic");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "proposal");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "series");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "ace");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "daku");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "discuss");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "newvod");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "bug");
//
//

//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "freedom");

        // 페이지 적어서 exception 발생
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "ombudsman");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "notice");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "tournament");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "starcraft2");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "war3");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "interview");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "event");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "pds");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "valuation");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "translation");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "broadcasting");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "league");

    }

}
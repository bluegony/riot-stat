package com.study.pgrservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 1000773 on 2019. 3. 6..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PgrDeleteCommentService.class})
public class PgrDeleteCommentServiceTest {

    @Autowired
    PgrDeleteCommentService pgrDeleteCommentService;

    @Test
    public void deletePgrComment() throws Exception {

//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "free2");
        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "freedom");
        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "bulpan");
//
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "humor");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "qna");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "spoent");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "genius");
//        pgrDeleteCommentService.deleteAllCommentInBoard("푸른고니", "free");

//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "recommend");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "gamenews");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "election");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "worldcup");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "olympic");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "proposal");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "series");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "ace");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "daku");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "discuss");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "newvod");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "bug");
//
//        // 페이지 적어서 exception 발생
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "ombudsman");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "notice");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "tournament");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "starcraft2");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "war3");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "interview");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "event");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "pds");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "valuation");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "translation");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "broadcasting");
//        pgrDeleteCommentService.deleteAllCommentInBoard("Theia", "league");


//        pgrDeleteCommentService.login("freedom");
//        String url = "https://pgr21.com/pb/pb.php?id=freedom&no=18687&divpage=4&sn=on&ss=on&sc=on&keyword=%ED%91%B8%EB%A5%B8%EA%B3%A0%EB%8B%88&cmt=on";
//        pgrDeleteCommentService.checkArticleAndDeleteComment(url);

    }

}
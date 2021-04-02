package com.web.pgrservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019. 3. 6..
 */
//@ExtendWith(SpringRunner.class)
@SpringBootTest
public class PgrDeleteCommentServiceTest {

    @Autowired
    PgrDeleteCommentService pgrDeleteCommentService;

    @Test
    public void deletePgrComment() throws Exception {
        pgrDeleteCommentService.run();
    }

}
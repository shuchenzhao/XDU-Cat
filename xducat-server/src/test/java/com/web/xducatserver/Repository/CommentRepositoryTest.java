package com.web.xducatserver.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void insertComment(){
        java.util.Date nowDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(nowDate.getTime());

        commentRepository.insertComment(2,"好肥的猫","姜黑耗",sqlDate);
        System.out.println("~");
    }
}
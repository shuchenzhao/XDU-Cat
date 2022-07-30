package com.web.xducatserver.Repository;

import com.web.xducatserver.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository ur;

    @Test
    void findAll(){
        System.out.println(ur.findAll());
    }
}
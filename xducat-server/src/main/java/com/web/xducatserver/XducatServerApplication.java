package com.web.xducatserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//下面这个是给mongoDB用的，如果你没有mongoDB请注释掉
@EnableMongoRepositories("com.web.xducatserver.Repository")
public class XducatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XducatServerApplication.class, args);
    }

}

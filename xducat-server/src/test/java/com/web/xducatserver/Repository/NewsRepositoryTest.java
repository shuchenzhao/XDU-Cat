package com.web.xducatserver.Repository;

import com.web.xducatserver.Entity.News;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
class NewsRepositoryTest {

    @Autowired
    NewsRepository newsRepository;

    @Test
    public void findAll(){
        List<News> news = newsRepository.findAll();

        System.out.println(news);
    }

}
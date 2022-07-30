package com.web.xducatserver.controller;

import com.mongodb.client.MongoCollection;
import com.web.xducatserver.Entity.News;
import com.web.xducatserver.Repository.NewsRepository;
import org.bson.BSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsHandler {

    @Autowired
    NewsRepository newsRepository;

    @RequestMapping(value = "/findAll")
    public List<News> findAll(){
        return newsRepository.findAll();
    }

}

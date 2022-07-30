package com.web.xducatserver.Repository;

import com.mongodb.client.MongoCollection;
import com.web.xducatserver.Entity.News;
import org.bson.BSONObject;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NewsRepository extends MongoRepository<News,String> {

}

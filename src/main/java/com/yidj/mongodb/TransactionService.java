package com.yidj.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * mongodb事务
 *
 * @author danjun.yi
 * @since 2020/12/15
 */
@Service
public class TransactionService {

    @Resource
    private MongoDatabase mongoDatabase;

    @Resource
    private MongoTemplate mongoTemplate;

    @Transactional
    public void transaction(){
        MongoCollection<Document> test1 = getTest1("test1");
        MongoCollection<Document> test2 = getTest1("test2");
        MongoCollection<Document> test3 = getTest1("test3");

        Document document = new Document()
                .append("id", 1).append("name", "test1");
        test1.insertOne(document);
        test2.insertOne(document);
        test3.insertOne(document);
    }


    @Transactional
    public void failTransaction(){
        MongoCollection<Document> test1 = getTest1("test1");
        MongoCollection<Document> test2 = getTest1("test2");
        MongoCollection<Document> test3 = getTest1("test3");

        Document document1 = new Document()
                .append("id", 2).append("name", "test2");
        Document document2 = new Document()
                .append("id", "test2").append("name", "test2");
        test1.insertOne(document1);
        test2.insertOne(document1);
        test3.insertOne(document2);

        throw new RuntimeException();
    }

    private MongoCollection<Document> getTest1(String test1) {
        return mongoTemplate.getDb().getCollection(test1);
    }

}

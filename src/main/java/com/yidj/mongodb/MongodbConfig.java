package com.yidj.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author danjun.yi
 * @since 2020/12/14
 */
@Configuration
@EnableTransactionManagement
public class MongodbConfig extends AbstractMongoClientConfiguration {

    @Bean
    public MongoClient mongoClient (){
       return  MongoClients.create("mongodb://test:test@127.0.0.1:27017/test?authSource=admin&authMechanism=SCRAM-SHA-1");
    }

    @Bean
   public MongoDatabase mongoDatabase(MongoTemplate mongoTemplate){
        return mongoTemplate.getDb();
   }

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory mongoDbFactory){
        return new MongoTransactionManager(mongoDbFactory);
    }

}


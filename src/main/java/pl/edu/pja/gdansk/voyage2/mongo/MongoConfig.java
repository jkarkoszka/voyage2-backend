package pl.edu.pja.gdansk.voyage2.mongo;

import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        mongoTemplate.setWriteConcern(WriteConcern.SAFE);
        return mongoTemplate;
    }
}

package ru.otus.lyamin.app.mongock;
//
//import com.github.cloudyrock.mongock.Mongock;
//import com.github.cloudyrock.mongock.SpringMongockBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//@Configuration
//public class MongockConfig {
//    private static final String CHANGELOGS_PACKAGE = "ru.otus.lyamin.app.changelog";
//    @Bean
//    public Mongock mongock(MongoTemplate mongoTemplate) {
//        return new SpringMongockBuilder(mongoTemplate, CHANGELOGS_PACKAGE)
//                .build();
//    }
//}
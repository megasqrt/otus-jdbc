package ru.kan.otus.libcat.mongock;

import com.github.cloudyrock.mongock.SpringMongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongockConfg {

    private static final String CHANGELOGS_PACKAGE = "ru.kan.otus.libcat.mongock.changelog";

    @Bean
    public SpringMongock springMongock(MongoTemplate mongoTemplate, Environment springEnvironment) {
        return new SpringMongockBuilder(mongoTemplate, CHANGELOGS_PACKAGE)
                .build();
    }

}

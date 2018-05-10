package me.hyuck.onimani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class OniManiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OniManiServerApplication.class, args);
    }
}

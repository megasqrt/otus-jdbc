package ru.kan.otus.libcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;

@EnableMongoRepositories
@SpringBootApplication
public class LibcatApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(LibcatApplication.class);
    }
}
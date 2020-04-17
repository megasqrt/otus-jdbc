package ru.kan.otus.libcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class LibcatApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(LibcatApplication.class);
    }
}
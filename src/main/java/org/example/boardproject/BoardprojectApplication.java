package org.example.boardproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BoardprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardprojectApplication.class, args);
    }

}

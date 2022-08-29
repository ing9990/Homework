package com.schoolproject.user;

/**
 * @author Taewoo
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;

@SpringBootApplication

public class PaperUserTestApp {

    public static void main(String[] args) {
        SpringApplication.run(PaperUserTestApp.class, args);
    }

    @Configuration
    @ComponentScan("com.schoolproject.user")
    @EnableJpaRepositories(basePackages = {
            "com.schoolproject.user.repository"
    })
    @EntityScan(basePackages = {
            "com.schoolproject.user.domain"
    })
    class Config {

    }


}

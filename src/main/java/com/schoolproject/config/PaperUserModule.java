package com.schoolproject.config;

/**
 * @author Taewoo
 */


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;

@Configuration
@ComponentScan("com.schoolproject.user")
@EnableJpaRepositories(basePackages = {
        "com.schoolproject.user.repository"
})
@EntityScan(basePackages = {
        "com.schoolproject.user.domain"
})
public class PaperUserModule {

}











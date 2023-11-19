package com.soft.apilab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiLabApplication.class, args);
    }

}

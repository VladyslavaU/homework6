package com.example.homework6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.example.homework6.model"})
public class Homework6Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework6Application.class, args);
    }

}

package com.example.Portfolio_Backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.Portfolio_Backend.repository")
public class mongo_config {

}

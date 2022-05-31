package com.api.springdiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDiscoveryServiceApplication.class, args);
    }

}

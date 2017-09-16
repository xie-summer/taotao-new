package com.taotao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ManageProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageProviderApplication.class, args);
    }
}

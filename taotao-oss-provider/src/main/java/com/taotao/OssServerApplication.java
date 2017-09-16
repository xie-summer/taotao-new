package com.taotao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Oss对象存储微服务
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OssServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssServerApplication.class, args);
    }
}

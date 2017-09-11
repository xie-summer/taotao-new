package com.taotao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 直接获取server中配置信息
 *
 * 获取git上的资源信息遵循如下规则
 /{application}/{profile}[/{label}]
 /{application}-{profile}.yml
 /{label}/{application}-{profile}.yml
 /{application}-{profile}.properties
 /{label}/{application}-{profile}.properties
 例如本例：
 可使用以下路径来访问
 microservice-config-client-dev.properties：
    http://localhost:8040/microservice-config-client-dev.properties
    http://localhost:8040/microservice-config-client/dev
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}

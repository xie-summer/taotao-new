package com.taotao.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
/**可以直接在yml中配置*/
//@PropertySource(value = "classpath:db.properties",ignoreResourceNotFound = true,encoding = "utf-8")
public class DruidDBConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

    @Value("${data.spring.datasource.url}")
    private String dbUrl;

    @Value("${data.spring.datasource.username}")
    private String username;

    @Value("${data.spring.datasource.password}")
    private String password;

    @Value("${data.spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${data.spring.datasource.initialSize}")
    private int initialSize;

    @Value("${data.spring.datasource.minIdle}")
    private int minIdle;

    @Value("${data.spring.datasource.maxActive}")
    private int maxActive;

    @Value("${data.spring.datasource.maxWait}")
    private int maxWait;

    @Value("${data.spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${data.spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${data.spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${data.spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${data.spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${data.spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${data.spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${data.spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${data.spring.datasource.filters}")
    private String filters;

    @Value("{data.spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }
}

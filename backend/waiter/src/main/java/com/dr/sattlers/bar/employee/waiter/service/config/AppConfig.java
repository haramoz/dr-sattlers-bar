package com.dr.sattlers.bar.employee.waiter.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.dr.sattlers.bar.db.BasicConnectionPool;

import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public BasicConnectionPool connectionPool() throws SQLException {
        return BasicConnectionPool.create(dbUrl, dbUsername, dbPassword);
    }
}
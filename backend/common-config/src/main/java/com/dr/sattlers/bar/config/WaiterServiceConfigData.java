package com.dr.sattlers.bar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "waiter-service")
public class WaiterServiceConfigData {
    private String welcomeMessage;
}

package com.dr.sattlers.bar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "waiter-service")
public class WaiterServiceConfigData {
    private String welcomeMessage;
    /*private String produceTopic1;
    private String produceTopic2;
    private String consumeTopic1;
    private String consumeTopic2;*/
}

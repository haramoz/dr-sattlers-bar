package com.dr.sattlers.bar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kitchen-service")
public class KitchenServiceConfigData {
    private String produceTopic1;
    private String produceTopic2;
    private String consumeTopic1;
    private String consumeTopic2;
}

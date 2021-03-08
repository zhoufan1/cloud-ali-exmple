package com.knife.authority.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "oauth.client.security.ignore")
@Data
public class KnifeClientIgnore {
    private List<String> urls;
    private List<String> ips;
}

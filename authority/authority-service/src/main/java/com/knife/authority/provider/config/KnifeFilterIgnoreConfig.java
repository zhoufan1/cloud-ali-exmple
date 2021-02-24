package com.knife.authority.provider.config;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import java.util.List;


@Configuration
@ConditionalOnExpression("!'${authorization.ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "authorization.ignore")
@Data
@RefreshScope
public class KnifeFilterIgnoreConfig {
    private List<String> urls = Lists.newArrayList();
}

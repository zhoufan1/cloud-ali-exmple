package com.example.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.client.config.listener.impl.PropertiesListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author Knife
 * @date 2019/12/4 15:36
 * @description
 */
@Configuration
@Slf4j
public class DynamincRoutingNacosConfig extends DynamicRoutingConfig {

    private final NacosConfigProperties nacosConfigProperties;

    public DynamincRoutingNacosConfig(RouteDefinitionWriter routeDefinitionWriter,
                                      NacosConfigProperties nacosConfigProperties) {
        super(routeDefinitionWriter);
        this.nacosConfigProperties = nacosConfigProperties;
    }

    @PostConstruct
    public void onMessage() throws Exception {
        ConfigService configService = nacosConfigProperties.configServiceInstance();
        configService.addListener("gateway", "gateway", listener());
    }

    public Listener listener() {
        return new PropertiesListener() {
            @Override
            public void innerReceive(Properties properties) {
                String data = JSON.toJSONString(properties);
                log.info("receive :{}", data);
               /* List<RouteDefinition> routeDefinitions = JSON.parseArray(data, RouteDefinition.class);
                routeDefinitions.forEach(route -> update(route));*/
            }
        };
    }
}

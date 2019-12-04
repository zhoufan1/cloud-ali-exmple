package com.example.gateway.config;

import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import reactor.core.publisher.Mono;
import java.util.concurrent.Executor;

/**
 * @author Knife
 * @date 2019/12/4 10:29
 * @description
 */
public abstract class DynamicRoutingConfig implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    private final RouteDefinitionWriter routeDefinitionWriter;

    public DynamicRoutingConfig(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void save(RouteDefinition route) {
        routeDefinitionWriter.save(Mono.just(route));
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public void  delete(String id){
        routeDefinitionWriter.delete(Mono.just(id));
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public void update(RouteDefinition route) {
        routeDefinitionWriter.delete(Mono.just(route.getId())).then(
                routeDefinitionWriter.save(Mono.just(route))
        );
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Slf4j
    public abstract static class DynamicListener implements Listener {
        @Override
        public Executor getExecutor() {
            return null;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            if (StringUtils.isBlank(configInfo)) {
                return;
            }
            innerReceive(configInfo);
        }

        protected abstract void innerReceive(String data);
    }


/*
    @NacosConfigListener(dataId = "gateway")
    public void onMessage(String config) {
        log.info("listener data:{}", config);
        List<RouteDefinition> routeDefinitions = JSON.parseArray(config, RouteDefinition.class);
        routeDefinitions.forEach(route -> {
            routeDefinitionWriter.delete(Mono.just(route.getId()));
            routeDefinitionWriter.save(Mono.just(route));
        });
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }*/


}

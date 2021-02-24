package com.knife.gateway.config;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import reactor.core.publisher.Mono;

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

    protected void save(RouteDefinition route) {
        routeDefinitionWriter.save(Mono.just(route));
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    protected void  delete(String id){
        routeDefinitionWriter.delete(Mono.just(id));
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    protected void update(RouteDefinition route) {
        routeDefinitionWriter.delete(Mono.just(route.getId())).then(
                routeDefinitionWriter.save(Mono.just(route))
        );
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

}

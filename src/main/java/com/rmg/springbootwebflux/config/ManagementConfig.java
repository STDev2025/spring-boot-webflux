package com.rmg.springbootwebflux.config;

import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.HealthEndpointGroups;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ManagementConfig {

    @Bean
    public HealthEndpoint healthEndpoint(HealthContributorRegistry registry, HealthEndpointGroups groups) {
        return new HealthEndpoint(registry, groups, Duration.ofSeconds(2));
    }
}

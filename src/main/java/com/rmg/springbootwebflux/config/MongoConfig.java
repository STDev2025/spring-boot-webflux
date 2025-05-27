package com.rmg.springbootwebflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import reactor.core.publisher.Mono;

@Configuration
@EnableMongoAuditing
public class MongoConfig {

    @Bean
    public ReactiveAuditorAware<String> auditorProvider() {
        return () -> Mono.just("system");
    }
}

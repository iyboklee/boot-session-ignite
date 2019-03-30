package com.github.iyboklee.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@Configuration
@EnableSpringHttpSession
public class SessionConfig {

    @Bean
    public SessionRepository sessionRepository(Ignite ignite) {
        IgniteCache<String, ExpiringSession> cache = ignite.getOrCreateCache("session_cache");
        return new MapSessionRepository(new IgniteCacheMapView<>(cache));
    }

}

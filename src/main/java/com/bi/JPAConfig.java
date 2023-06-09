package com.bi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.bi.config.AuditorAwareImpl;
import com.bi.model.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JPAConfig {
	@Bean
    public AuditorAware<User> auditorAware() {
        return new AuditorAwareImpl();
    }
}

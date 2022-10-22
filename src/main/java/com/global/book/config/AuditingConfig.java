package com.global.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
//@EnableJpaAuditing(auditorAwareRef="name of bean method or bean ref in xml")
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class AuditingConfig {
	
	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}
}

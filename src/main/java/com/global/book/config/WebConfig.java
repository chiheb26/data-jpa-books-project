package com.global.book.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.amazonaws.services.s3.AbstractAmazonS3;
import com.amazonaws.services.s3.AmazonS3;

@Configuration
//@EnableJpaAuditing(auditorAwareRef="name of bean method or bean ref in xml")
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}
	
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:bundle/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean
	@Override
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	@Bean
	public AmazonS3 amazonS3() {
		return new AmazonS3Impl();
	}
}

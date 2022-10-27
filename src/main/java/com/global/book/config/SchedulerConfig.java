package com.global.book.config;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@Configuration
@ConditionalOnProperty(name = "scheduler.enabled",matchIfMissing = true)
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
@EnableAsync
public class SchedulerConfig implements AsyncConfigurer{

		@Bean
		public LockProvider lockProvider(DataSource dataSource) {
			return new JdbcTemplateLockProvider(JdbcTemplateLockProvider.Configuration.builder()
					.withJdbcTemplate(new JdbcTemplate(dataSource)).usingDbTime().build());
		}
		
		// will be applied only for method with @Async(name="threadPoolTaskExecutor") : method level
		//or implement AsyncConfigurer and override : public Executor getAsyncExecutor() 
		// and it will be applied in application level
		@Bean(name="threadPoolTaskExecutor")
		public Executor asyncExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(4);
			executor.setMaxPoolSize(4);
			executor.setQueueCapacity(50);
			executor.setThreadNamePrefix("AsynchThread::");
			executor.initialize();
			return executor;
		}
		// will be applied in application level
		@Override
		public Executor getAsyncExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(4);
			executor.setMaxPoolSize(4);
			executor.setQueueCapacity(50);
			executor.setThreadNamePrefix("AsynchThread::");
			executor.initialize();
			return executor;
		}
}

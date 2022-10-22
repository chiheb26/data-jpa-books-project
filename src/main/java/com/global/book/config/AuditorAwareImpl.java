package com.global.book.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
// public class AuditorAwareImpl implements AuditorAware<typeof(createdBy/lastModiriedBy)>{
public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO should get username from spring security
		//use spring security to retrieve curently logged-in user
		return Optional.of("test user");
	}

}

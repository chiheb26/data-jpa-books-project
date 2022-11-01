package com.global.book.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.error.DuplicateRecordException;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;



@Service
public class AuthorService extends BaseService<Author,Long> {
	
	Logger log = LoggerFactory.getLogger(AuthorService.class);
	@Autowired
	private AuthorRepo authorRepo;
	
	
	@Override
	//@Cacheable(key="#author", value="findAllauthors")
	public List<Author> findAll(){
		return super.findAll();
	}
	
	@Override
	//@Cacheable(key="#id", value="findAuthorById")
	public Author findById(Long id){
		return super.findById(id);
	}
	@Override
	//@CacheEvict(key="#author", value={"findAllauthors","findAuthorById","findAuthorByEmail"},allEntries = true)
	public Author insert(Author entity) {
		if(!entity.getEmail().isBlank()) {
			Optional<Author> author = findByEmail(entity.getEmail());
			log.info("Author name -> {} , Email -> {}",entity.getName(),entity.getEmail());
			if(author.isPresent()) {
				log.error("There is another author registered with this email.");
				throw new DuplicateRecordException("There is another author registered with this email.");
			}
		}
		return super.insert(entity);
	}
	
	@Override
	//@CacheEvict(key="#author", value={"findAllauthors","findAuthorById","findAuthorByEmail"},allEntries = true)
	//@Caching(evict= {@CacheEvict("author"), @CacheEvict(value="author", key="#author.id")})
	public Author update(Author author) {
		Author current = this.findById(author.getId());
		current.setName(author.getName());
		return super.update(current);
	}
	
	public List<Author> findByAuthorSpec(AuthorSearch search){
		AuthorSpec spec = new AuthorSpec(search);
		return authorRepo.findAll(spec);
		
	}
	
	//@Cacheable(key="#email", value="findAuthorByEmail")
	public Optional<Author> findByEmail(String email){
		return authorRepo.findByEmail(email);
	}
	
	@Async("threadPoolTaskExecutor")
	//@Async
	//@Cacheable(key="#email", value="findAuthorByEmail")
	public CompletableFuture<Author> findByEmailAsync(String email){
		return CompletableFuture.completedFuture(authorRepo.findByEmail(email).get());
	}


}

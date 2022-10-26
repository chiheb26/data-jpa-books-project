package com.global.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.error.DuplicateRecordException;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;



@Service
public class AuthorService extends BaseService<Author,Long> {
	
	@Autowired
	private AuthorRepo authorRepo;
	
	@Override
	public Author insert(Author entity) {
		if(!entity.getEmail().isBlank()) {
			Optional<Author> author = findByEmail(entity.getEmail());
			if(author.isPresent()) {
				throw new DuplicateRecordException("There is another author registered with this email.");
			}
		}
		return super.insert(entity);
	}
	
	@Override
	public Author update(Author author) {
		Author current = this.findById(author.getId());
		current.setName(author.getName());
		return super.update(current);
	}
	
	public List<Author> findByAuthorSpec(AuthorSearch search){
		AuthorSpec spec = new AuthorSpec(search);
		return authorRepo.findAll(spec);
		
	}
	public Optional<Author> findByEmail(String email){
		return authorRepo.findByEmail(email);
	}


}

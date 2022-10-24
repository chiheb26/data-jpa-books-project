package com.global.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;



@Service
public class AuthorService extends BaseService<Author,Long> {
	
	@Autowired
	private AuthorRepo authorRepo;
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

}

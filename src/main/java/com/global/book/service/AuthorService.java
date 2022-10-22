package com.global.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.entity.Author;
import com.global.book.repository.AuthorRepo;



@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepo authorRepo;
	
	public Author findById(Long id) {
		return authorRepo.findById(id).orElseThrow();
	}
	public Author getReferenceById(Long id) {
		return authorRepo.getReferenceById(id);
	}
	public List<Author> findAll() {
		return authorRepo.findAll();
	}
	
	public Author insert(Author author) {
		if(author.getId()!= null) {
			throw new RuntimeException("ID Is given !");
		}
		return authorRepo.save(author);
	}
	
	public List<Author> insertAll(List<Author> authors) {

		return authorRepo.saveAll(authors);
	}
	public Author update(Author author) {
		Author current = this.findById(author.getId());
		current.setName(author.getName());
		return authorRepo.save(current);
	}
	public void delete(Long id) {
		 authorRepo.deleteById(id);
	}
}

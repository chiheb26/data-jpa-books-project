package com.global.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public Book findById(Long id) {
		return bookRepo.findById(id).orElseThrow();
	}

	public List<Book> findAll() {
		return bookRepo.findAll();
	}
	
	public Book insert(Book book) {
		if(book.getId()!= null) {
			throw new RuntimeException("ID Is given !");
		}
		return bookRepo.save(book);
	}
	public Book update(Book book) {
		Book current = this.findById(book.getId());
		current.setName(book.getName());
		current.setPrice(book.getPrice());
		current.setAuthor(book.getAuthor());
		return bookRepo.save(current);
	}
	public void delete(Long id) {
		 bookRepo.deleteById(id);
	}
}

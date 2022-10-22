package com.global.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;

@Service
public class BookService extends BaseService<Book,Long>{
	
	
	@Autowired
	private BookRepo bookRepo;
	
	@Override
	public Book update(Book book) {
		Book current = this.findById(book.getId());
		current.setName(book.getName());
		current.setPrice(book.getPrice());
		current.setAuthor(book.getAuthor());
		return super.update(current);
	}

	public int deleteAllByAuthorId(Long id) {
		return bookRepo.deleteAllByAuthorId(id);
	}
}

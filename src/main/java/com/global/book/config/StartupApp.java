package com.global.book.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.service.AuthorService;
import com.global.book.service.BookService;

@Component
public class StartupApp implements CommandLineRunner{
	
	
	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		// adding authors
		
		Author author1 = new Author();
		author1.setName("author 1");
		
		Author author2 = new Author();
		author2.setName("author 2");
		
		Author author3 = new Author();
		author3.setName("author 3");
		
		authorService.insertAll(Arrays.asList(author1,author2,author3));
		
		// adding books
		
		Book book1 = new Book();
		book1.setName("Java JPA");
		book1.setPrice(200);
		book1.setAuthor(authorService.getReferenceById(1L));
		
		Book book2 = new Book();
		book2.setName("Database MYSQL");
		book2.setPrice(300);
		book2.setAuthor(authorService.getReferenceById(2L));
		
		Book book3 = new Book();
		book3.setName("Python");
		book3.setPrice(120);
		book3.setAuthor(authorService.getReferenceById(3L));
		
		bookService.insertAll(Arrays.asList(book1,book2,book3));
		
		
	}

}

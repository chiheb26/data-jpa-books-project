package com.global.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Book;
import com.global.book.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.findById(id));
	}
	
	@GetMapping("")
	public ResponseEntity<List<Book>> findAll() {
		return ResponseEntity.ok(bookService.findAll());
	}
	@PostMapping("")
	public ResponseEntity<Book> insert(@RequestBody Book book) {
		
		return ResponseEntity.ok(bookService.insert(book));
	}
	
	@PutMapping("")
	public ResponseEntity<Book> update(Book book) {
		return ResponseEntity.ok(bookService.update(book));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Book> delete(@PathVariable Long id) {
		bookService.delete(id);
		return ResponseEntity.ok(null);

	}
}
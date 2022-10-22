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

import com.global.book.entity.Author;
import com.global.book.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> findById(@PathVariable Long id) {
		return ResponseEntity.ok(authorService.findById(id));
	}
	
	@GetMapping("")
	public ResponseEntity<List<Author>> findAll() {
		return ResponseEntity.ok(authorService.findAll());
	}
	@PostMapping("")
	public ResponseEntity<Author> insert(@RequestBody Author author) {
		
		return ResponseEntity.ok(authorService.insert(author));
	}
	
	@PutMapping("")
	public ResponseEntity<Author> update(Author author) {
		return ResponseEntity.ok(authorService.update(author));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> delete(@PathVariable Long id) {
		authorService.delete(id);
		return ResponseEntity.ok(null);
	}
}

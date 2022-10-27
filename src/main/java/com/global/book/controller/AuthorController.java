package com.global.book.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.service.AuthorService;

@RestController
@RequestMapping("/author")
@Validated
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> findById(@PathVariable @Min(1) @Max(9999) Long id) {
		return ResponseEntity.ok(authorService.findById(id));
	}
	
	@GetMapping("")
	public ResponseEntity<List<Author>> findAll() {
		return ResponseEntity.ok(authorService.findAll());
	}
	@PostMapping("")
	public ResponseEntity<Author> insert(@RequestBody @Valid Author author) {
		
		return ResponseEntity.ok(authorService.insert(author));
	}
	
	@PutMapping("")
	public ResponseEntity<Author> update(@RequestBody @Valid Author author) {
		return ResponseEntity.ok(authorService.update(author));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> delete(@PathVariable Long id) {
		authorService.delete(id);
		return ResponseEntity.ok(null);
	}
	@PostMapping("/spec")
	public ResponseEntity<List<Author>> findByAuthorSpec(@RequestBody AuthorSearch search){
		return ResponseEntity.ok(authorService.findByAuthorSpec(search));
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<CompletableFuture<Author>> findByEmailAsync(@PathVariable @Email String email){
		return ResponseEntity.ok(authorService.findByEmailAsync(email));

}
}
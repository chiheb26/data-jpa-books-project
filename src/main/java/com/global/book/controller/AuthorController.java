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
import com.global.book.entity.Book;
import com.global.book.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Author Controller")

@RestController
@RequestMapping("/author")
@Validated
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	@Operation(summary = "Get an author by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the book", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Author> findById(@Parameter(example = "20", name = "Book Id") @PathVariable @Min(1) @Max(9999) Long id) {
		return ResponseEntity.ok(authorService.findById(id));
	}
	@Operation(summary = "Get all authors")
	@GetMapping("")
	public ResponseEntity<List<Author>> findAll() {
		return ResponseEntity.ok(authorService.findAll());
	}
	@Operation(summary = "add an author")
	@PostMapping("")
	public ResponseEntity<Author> insert(@RequestBody @Valid Author author) {
		
		return ResponseEntity.ok(authorService.insert(author));
	}
	@Operation(summary = "update an author")
	@PutMapping("")
	public ResponseEntity<Author> update(@RequestBody @Valid Author author) {
		return ResponseEntity.ok(authorService.update(author));
	}
	@Operation(summary = "delete an author by its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> delete(@PathVariable Long id) {
		authorService.delete(id);
		return ResponseEntity.ok(null);
	}
	@Operation(summary = "get authors by search")
	@PostMapping("/spec")
	public ResponseEntity<List<Author>> findByAuthorSpec(@RequestBody AuthorSearch search){
		return ResponseEntity.ok(authorService.findByAuthorSpec(search));
	}
	@Operation(summary = "get an author by its email")
	@GetMapping("/email/{email}")
	public ResponseEntity<CompletableFuture<Author>> findByEmailAsync(@PathVariable @Email String email){
		return ResponseEntity.ok(authorService.findByEmailAsync(email));

}
}
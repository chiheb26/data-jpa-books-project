package com.global.book.controller;

import java.util.List;
import javax.validation.Valid;
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
import com.global.book.entity.BookDto;
import com.global.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Author Controller")
@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Operation(summary = "Get a book by its id")
	@GetMapping("/{id}")
	public ResponseEntity<BookDto> findById(@PathVariable Long id) {
		Book book = bookService.findById(id);
		BookDto dto = new BookDto();
		dto.setId(book.getId());
		dto.setName(book.getName());
		dto.setPrice(book.getPrice());
		dto.setAuthor(book.getAuthor());
		return ResponseEntity.ok(dto);
	}
	@Operation(summary = "Get all books")
	@GetMapping("")
	public ResponseEntity<List<Book>> findAll() {
		return ResponseEntity.ok(bookService.findAll());
	}
	@Operation(summary = "add a book")
	@PostMapping("")
	public ResponseEntity<Book> insert(@RequestBody @Valid BookDto dto) {
		Book book = new Book();
		book.setId(dto.getId());
		book.setName(dto.getName());
		book.setPrice(dto.getPrice());
		book.setAuthor(dto.getAuthor());
		
		return ResponseEntity.ok(bookService.insert(book));
	}
	@Operation(summary = "update a book")
	@PutMapping("")
	public ResponseEntity<Book> update(@RequestBody @Valid Book book) {
		return ResponseEntity.ok(bookService.update(book));
	}
	@Operation(summary = "delete a book by its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> delete(@PathVariable Long id) {
		bookService.delete(id);
		return ResponseEntity.ok(null);

	}
	@Operation(summary = "delete books by their author id")
	@DeleteMapping("/author/{id}")
	public ResponseEntity<Integer> deleteByAuthorId(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.deleteAllByAuthorId(id));
	}
}

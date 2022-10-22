package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.book.entity.Author;
import com.global.book.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

	@Override
	@EntityGraph(attributePaths = {"author"})
	Optional<Book> findById(Long id);
	
	//@Query("select book from Book book where book.id = :id")
	//Optional<Book> findByIdCustom(Long id);
	
	@Override
	@EntityGraph("loadAuthor")
	List<Book> findAll();
	
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("delete from Book book where book.author.id = :id")
	int deleteAllByAuthorId(Long id);
	
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("delete from Book book where book.author = :author")
	int deleteAllByAuthor(Author author);
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("delete from Book book where book.author.id in :ids")
	int deleteAllByAuthorsIds(List<Long> ids);
	
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("delete from Book book where book.author in :authors")
	int deleteAllByAuthors(List<Author> authors);
}

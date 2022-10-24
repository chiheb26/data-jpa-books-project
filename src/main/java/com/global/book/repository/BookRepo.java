package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedStoredProcedureQuery;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Author;
import com.global.book.entity.Book;

@Repository
public interface BookRepo extends BaseRepository<Book, Long> {

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
	
	
	
	//@Procedure
	//int GET_BOOK_BY_AUTHOR(String author_id_in);
	//@Procedure("GET_BOOK_BY_AUTHOR")
	//int GetBookByAuthor(String author_id_in);
	////////////////////////////////// 
	//with  @NamedStoredProcedureQuery in book class
	//@Procedure(name="Book.getBookByAuthor")
	//int GetBookByAuthor(String author_id_in);
	/////////////////////////////////
	//@Query(value="GET_BOOK_BY_AUTHOR(:author_id_in",nativeQuery=true)
	//int GetBookByAuthor(@Param("author_id_in") String author_id_in);
	
	
	//@Transactional
	//@Query(value = "UPDATE Book b SET b.isDeleted = false WHERE b.auther.id = ?1")
	//@Modifying
	//public void restoreByAuthorId(Long autherId);
}

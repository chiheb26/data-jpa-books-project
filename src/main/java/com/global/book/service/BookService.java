package com.global.book.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;

@Service
public class BookService extends BaseService<Book,Long>{
	
	
	@Autowired
	private BookRepo bookRepo;
	
	private final static String BOOKS_PROC=".GET_BOOK_BY_AUTHOR";
//	@Autowired
//	private EntityManager entityManager;
//	@Autowired
//	private Environment env;
	
	
	
//	public int getBookCountByAuthorId(int authorId) {
//		String dbName = env.getProperty("spring.jpa.properties.hibernate.default_schema");
//		StoredProcedureQuery query=entityManager.createStoredProcedureQuery(dbName+BOOKS_PROC);
//		query.registerStoredProcedureParameter("author_id",String.class,ParameterMode.IN);
//		query.registerStoredProcedureParameter("result_param",Integer.class,ParameterMode.OUT);
//		query.setParameter("author_id",authorId);
//		int count = ((Number) query.getOutputParameterValue("result_param")).intValue();
//	    return count;
//    }
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

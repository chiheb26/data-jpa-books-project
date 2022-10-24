package com.global.book.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.entity.Book;

public class AuthorSpec implements Specification<Author> {
	
	private AuthorSearch search;
	
	public AuthorSpec(AuthorSearch search) {
		super();
		this.search = search;
	}

	@Override
	public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		List<Predicate> predicates = new ArrayList<>();
		Join<Author,Book> bookJoin= root.join("books",JoinType.LEFT);
		if(search.getAuthorName() != null && !search.getAuthorName().isEmpty() && !search.getAuthorName().isBlank()) {
			 predicates.add(cb.like(root.get("name"), "%"+search.getAuthorName()+"%"));
		}
		if(search.getEmail() != null && !search.getEmail().isEmpty() && !search.getEmail().isBlank()) {
			 predicates.add(cb.like(root.get("email"), search.getEmail()));
		}
		if(search.getIpAddress() != null && !search.getIpAddress().isEmpty() && !search.getIpAddress().isBlank()) {
			 predicates.add(cb.like(root.get("ipAddress"), "%"+search.getIpAddress()+"%"));
		}
		if(search.getBookName() != null && !search.getBookName().isEmpty() && !search.getBookName().isBlank()) {
			 predicates.add(cb.like(bookJoin.get("name"), "%"+search.getBookName()+"%"));
		}
		if(search.getPrice() != null ) {
			 predicates.add(cb.greaterThanOrEqualTo(bookJoin.get("price"), search.getPrice()));
		}
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}

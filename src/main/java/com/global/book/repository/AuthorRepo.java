package com.global.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Author;

@Repository
public interface AuthorRepo extends BaseRepository<Author,Long>,JpaSpecificationExecutor<Author>{
	
//	@Transactional
//	@Query(value = "UPDATE Author a SET a.isDeleted = false WHERE a.id = ?1")
//	@Modifying
//	public void restoreById(Long id);

	Optional<Author> findByEmail(String email);

	
}

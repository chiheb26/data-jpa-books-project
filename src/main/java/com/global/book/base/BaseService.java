package com.global.book.base;

import java.util.List;

import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;

import com.global.book.error.RecordNotFoundException;



@MappedSuperclass
public class BaseService <T extends BaseEntity<ID>, ID extends Number> {
	
	@Autowired
	protected BaseRepository<T,ID> baseRepository;
		
	
	public T findById(ID id) {
		return baseRepository.findById(id)
				.orElseThrow(()->new RecordNotFoundException("Record With ID = "+id+" Not Found."));
	}
	public T getReferenceById(ID id) {
		return baseRepository.getReferenceById(id);
	}
	public List<T> findAll() {
		return baseRepository.findAll();
	}
	
	public T insert(T entity) {
		if(entity.getId()!= null) {
			throw new RuntimeException("ID Is given !");
		}
		return baseRepository.save(entity);
	}
	
	public List<T> insertAll(List<T> entities) {

		return baseRepository.saveAll(entities);
	}
	public T update(T entity) {
		return baseRepository.save(entity);
	}
	public void delete(ID id) {
		baseRepository.deleteById(id);
	}
}

package com.global.book.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseEntity;
import com.global.book.validator.IpAddress;

@Entity
@Table(name="authors")
public class Author extends BaseEntity<Long>{
	
	@NotBlank(message="Name field can't be empty !")
	private String name;
	
	//@Pattern(regexp="^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$")
	
	//@IpAddress(message="Must be a valid IP address")
	@IpAddress
	private String ipAddress;
	@Email(message="{validation.constraints.email.message}")
	private String email;
	private String imagePath;

	//@NotEmpty
	@JsonManagedReference
	@OneToMany(mappedBy = "author")
	private List<Book> books = new ArrayList<>();	
	
	
	// between ()
	@Formula("(select count(*) from books b where b.author_id = id)")
	private long bookCount;
	
	public long getBookCount() {
		return bookCount;
	}
	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}
	
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public void addBook(Book book) {
		this.books.add(book);
		book.setAuthor(this);
	}
	public void removeBook(Book book) {
		this.books.remove(book);
		book.setAuthor(null);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}

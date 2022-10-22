package com.global.book.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.PostLoad;
import javax.persistence.NamedAttributeNode;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.book.base.BaseEntity;

@Entity
@Table(name="books")
@NamedEntityGraph(name="loadAuthor",attributeNodes=@NamedAttributeNode("author"))
public class Book extends BaseEntity<Long>{
	
	private String name;

	private double price;
	

	
	@Transient
	private double discount;
	
	// between ()
	@Formula("(select count(*) from books)")
	private long bookCount;
	
	public long getBookCount() {
		return bookCount;
	}
	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	@JsonBackReference
	private Author author;
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@PostLoad
	private void calcDiscount() {
		this.setDiscount(price * .25);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

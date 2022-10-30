package com.global.book.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PostLoad;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.book.base.BaseEntity;

import io.swagger.v3.oas.annotations.media.Schema;

@NamedStoredProcedureQuery(name="Book.getBookByAuthor",
procedureName="GET_BOOK_BY_AUTHOR",parameters= {
@StoredProcedureParameter(mode=ParameterMode.IN,name="author_id_in",type = String.class),
@StoredProcedureParameter(mode=ParameterMode.OUT,name="book_count",type = Integer.class)
})

///////////////////////////////
//soft delete
//@SQLDelete(sql = "update books set is_deleted=true where id=?")
//@Where(clause = "is_deleted=false")
///////////////////////////////
@Entity
@Table(name="books")
@NamedEntityGraph(name="loadAuthor",attributeNodes=@NamedAttributeNode("author"))
@Schema(name = "Book Entity")
public class Book extends BaseEntity<Long>{
	
	@NotNull(message="Name field can't be null !")
	private String name;
	@Min(5)
	@Max(500)
	private double price;
	
//	private boolean isDeleted;
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	@JsonBackReference
	private Author author;
	
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
//	public boolean isDeleted() {
//		return isDeleted;
//	}
//	public void setDeleted(boolean isDeleted) {
//		this.isDeleted = isDeleted;
//	}
	
	
}

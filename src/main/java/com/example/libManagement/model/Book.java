package com.example.libManagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	private int bookId;
	private String bookName ; 
	private String description;
	private boolean checked;
	private Date checkOutDate;
	private Date dueDate;
	
	public Book() {
	}

	public Book(int bookId, String bookName, String description, boolean checked, Date checkOutDate, Date dueDate) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.description = description;
		this.checked = checked;
		this.checkOutDate = checkOutDate;
		this.dueDate = dueDate;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}

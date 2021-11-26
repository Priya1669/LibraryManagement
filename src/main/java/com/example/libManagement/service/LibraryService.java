package com.example.libManagement.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libManagement.model.Book;

@Service
public class LibraryService {

	@Autowired
	private LibraryRepository libRepo;
	
	
	/*
	 * @Autowired private Book book;
	 */

	/*
	 * public Iterable<Book> getAvailableBooks() {
	 * 
	 * List<Integer> ids = new ArrayList<>(); if(book.isChecked() != true) {
	 * ids.add(book.getBookId()); } return libRepo.findAllById(ids); }
	 * 
	 * public void addBooks(Book book) { libRepo.save(book); }
	 * 
	 */
	
	public List<Book> getAvailableBooks() {
		List<Book> books = new ArrayList<>();
		libRepo.findAll().forEach(books::add);
		
		return books.stream().filter(a -> a.isChecked() == (false))
		.collect(Collectors.toList());
	}
	
	public List<Book> getCheckedOutBooks() {
		List<Book> books = new ArrayList<>();
		libRepo.findAll().forEach(books::add);
		
		return books.stream().filter(a -> a.isChecked() == (true))
		.collect(Collectors.toList());
	}
	
	public List<Book> getCheckedBooksOutofDue() {
		
		Date today = Calendar.getInstance().getTime();
		List<Book> books = new ArrayList<>();
		libRepo.findAll().forEach(books::add);
		
		return books.stream().filter(a -> a.isChecked() == (true)).filter(a -> a.getDueDate().before(new Date()))
		.collect(Collectors.toList());
	}
	
	
	public void addBooks(Book book) {
		libRepo.save(book);
	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		libRepo.findAll().forEach(books::add);
		
		return books;
		
	}

	public void checkOutBookById(Integer id) {
		Book book = libRepo.findById(id).get();
		book.setChecked(true);
		Date today = Calendar.getInstance().getTime();
		book.setCheckOutDate(today);
		
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DATE, 10);
		
		book.setDueDate(c.getTime());
		
		libRepo.save(book);
	}
}

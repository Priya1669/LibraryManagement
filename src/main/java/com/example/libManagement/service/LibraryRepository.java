package com.example.libManagement.service;

import org.springframework.data.repository.CrudRepository;

import com.example.libManagement.model.Book;

public interface LibraryRepository extends CrudRepository<Book, Integer>{

}

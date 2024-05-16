package ru.r3is.libka.services;

import ru.r3is.libka.dto.Book;

import java.util.List;

public interface BookService {
	Long addNew(Book book);

	List<Book> findAll();

	Book findById(Long id);

	Book findByName(String name);
}

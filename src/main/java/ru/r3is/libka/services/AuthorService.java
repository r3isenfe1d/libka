package ru.r3is.libka.services;

import ru.r3is.libka.dto.Author;

import java.util.List;
import java.util.NoSuchElementException;

public interface AuthorService {
	Long addNew(Author author);

	Long addToAuthorBooks(Long authorId, Long bookId) throws NoSuchElementException;

	List<Author> findAll() throws NoSuchElementException;

	Author findById(Long id) throws NoSuchElementException;
}

package ru.r3is.libka.services;

import org.springframework.stereotype.Service;
import ru.r3is.libka.dto.Author;
import ru.r3is.libka.dto.Book;
import ru.r3is.libka.entities.AuthorEntity;
import ru.r3is.libka.entities.BookEntity;
import ru.r3is.libka.repos.AuthorRepo;
import ru.r3is.libka.repos.BookRepo;

import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {
	private final AuthorRepo authorRepo;
	private final BookRepo bookRepo;

	public AuthorServiceImpl(AuthorRepo authorRepo, BookRepo bookRepo) {
		this.authorRepo = authorRepo;
		this.bookRepo = bookRepo;
	}

	public Long addNew(Author author) {
		AuthorEntity authorEntity = authorRepo.findByName(author.getName());
		if (authorEntity != null) {
			return authorEntity.getId();
		}
		authorEntity = new AuthorEntity();
		authorEntity.setName(author.getName());

		AuthorEntity savedAuthorEntity = authorRepo.save(authorEntity);
		return savedAuthorEntity.getId();
	}

	public Long addToAuthorBooks(Long authorId, Long bookId) throws NoSuchElementException {
		BookEntity bookEntity = bookRepo.findById(bookId).orElseThrow(NoSuchElementException::new);
		AuthorEntity authorEntity = authorRepo.findById(authorId).orElseThrow(NoSuchElementException::new);

		authorEntity.addToAuthorBooks(bookEntity);
		bookEntity.setAuthor(authorEntity);

		authorRepo.save(authorEntity);
		bookRepo.save(bookEntity);

		return bookEntity.getId();
	}

	public List<Author> findAll() throws NoSuchElementException {
		List<AuthorEntity> authorEntityList = authorRepo.findAll();
		if (authorEntityList.isEmpty()) {
			throw new NoSuchElementException("[WARNING] Can't find any author");
		}

		List<Author> authors = new ArrayList<>();
		for (AuthorEntity authorEntity : authorEntityList) {
			Set<Book> books = new HashSet<>();
			for (BookEntity bookEntity : authorEntity.getAuthorBooks()) {
				books.add(new Book(bookEntity.getName(), bookEntity.getAuthor().getName()));
			}
			authors.add(new Author(authorEntity.getName(), books));
		}
		return authors;
	}

	public Author findById(Long id) throws NoSuchElementException {
		AuthorEntity authorEntity = authorRepo.findById(id).orElseThrow(NoSuchElementException::new);

		Set<Book> books = new HashSet<>();
		for (BookEntity bookEntity : authorEntity.getAuthorBooks()) {
			books.add(new Book(bookEntity.getName(), bookEntity.getAuthor().getName()));
		}
		return new Author(authorEntity.getName(), books);
	}
}

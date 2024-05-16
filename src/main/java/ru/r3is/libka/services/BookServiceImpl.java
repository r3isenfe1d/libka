package ru.r3is.libka.services;

import org.springframework.stereotype.Service;
import ru.r3is.libka.dto.Book;
import ru.r3is.libka.entities.AuthorEntity;
import ru.r3is.libka.entities.BookEntity;
import ru.r3is.libka.repos.AuthorRepo;
import ru.r3is.libka.repos.BookRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {
	private final BookRepo bookRepo;
	private final AuthorRepo authorRepo;

	public BookServiceImpl(BookRepo bookRepo, AuthorRepo authorRepo) {
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
	}

	public Long addNew(Book book) {
		AuthorEntity authorEntity = authorRepo.findByName(book.getAuthorName());
		if (authorEntity == null) {
			authorEntity = new AuthorEntity();
			authorEntity.setName(book.getAuthorName());
		}

		BookEntity bookEntity = new BookEntity(book.getName(), authorEntity);
		authorEntity.addToAuthorBooks(bookEntity);

		BookEntity savedBook = bookRepo.save(bookEntity);
		return savedBook.getId();
	}

	public List<Book> findAll() {
		List<BookEntity> bookEntityList = bookRepo.findAll();
		if (bookEntityList.isEmpty()) {
			throw new NoSuchElementException("[WARNING] Can't find any book");
		}
		List<Book> books = new ArrayList<>();
		for (BookEntity bookEntity : bookEntityList) {
			books.add(new Book(bookEntity.getName(), bookEntity.getAuthor().getName()));
		}
		return books;
	}

	public Book findById(Long id) {
		BookEntity bookEntity = bookRepo.findById(id).orElseThrow(NoSuchElementException::new);
		return new Book(bookEntity.getName(), bookEntity.getAuthor().getName());
	}

	public Book findByName(String name) {
		BookEntity bookEntity = bookRepo.findByName(name);
		if (bookEntity == null) {
			throw new NoSuchElementException("[WARNING] Book doesn't exist");
		}
		return new Book(bookEntity.getName(), bookEntity.getAuthor().getName());
	}
}

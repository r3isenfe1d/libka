package ru.r3is.libka.dto;

import java.util.HashSet;
import java.util.Set;

public class Author {
	private String name;
	private final Set<Book> books;

	public Author(String name, Set<Book> books) {
		this.name = name;
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	public void deleteBook(String bookName) {
		this.books
				.stream()
				.filter(each -> each.getName().equals(bookName))
				.findFirst()
				.ifPresent(this.books::remove);
	}
}

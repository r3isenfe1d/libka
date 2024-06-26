package ru.r3is.libka.dto;

import java.util.Set;

public class User {
	private String login;
	private final Set<Book> books;

	public User(String login, Set<Book> books) {
		this.login = login;
		this.books = books;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

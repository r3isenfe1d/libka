package ru.r3is.libka.dto;

public class Book {
	private String name;
	private String authorName;

	public Book(String name, String author) {
		this.name = name;
		this.authorName = author;
	}

	private Book() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}

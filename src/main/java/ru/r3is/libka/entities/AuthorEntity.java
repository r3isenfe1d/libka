package ru.r3is.libka.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = AuthorEntity.tableName)
public class AuthorEntity {
	public static final String tableName = "authors";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, length = 64)
	private String name;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	private final Set<BookEntity> authorBooks = new HashSet<>();

	public AuthorEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BookEntity> getAuthorBooks() {
		return authorBooks;
	}

	public void addToAuthorBooks(BookEntity book) {
		this.authorBooks.add(book);
	}
}

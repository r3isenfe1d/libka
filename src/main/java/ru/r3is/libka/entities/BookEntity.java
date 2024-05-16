package ru.r3is.libka.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = BookEntity.bookTableName)
public class BookEntity {
	public static final String bookTableName = "books";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, length = 64)
	private String name;

	@ManyToOne
	private AuthorEntity author;

	@ManyToMany(mappedBy = "receivedBooks")
	private final Set<UserEntity> givenToUsers = new HashSet<>();

	public BookEntity() {
	}

	public BookEntity(String name, AuthorEntity author) {
		this.name = name;
		this.author = author;
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

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}

	public Set<UserEntity> getGivenToUsers() {
		return givenToUsers;
	}

	public void addGivenToUsers(UserEntity givenToUsers) {
		this.givenToUsers.add(givenToUsers);
	}
}

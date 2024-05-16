package ru.r3is.libka.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = UserEntity.userTableName)
public class UserEntity {
	public static final String userTableName = "users";
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(
			name = "user_book",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	private final Set<BookEntity> receivedBooks = new HashSet<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false, length = 64)
	private String login;

	public UserEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<BookEntity> getReceivedBooks() {
		return receivedBooks;
	}

	public void addReceivedBooks(BookEntity receivedBooks) {
		this.receivedBooks.add(receivedBooks);
	}
}

package ru.r3is.libka.services;

import org.springframework.stereotype.Service;
import ru.r3is.libka.dto.Book;
import ru.r3is.libka.dto.User;
import ru.r3is.libka.entities.BookEntity;
import ru.r3is.libka.entities.UserEntity;
import ru.r3is.libka.repos.BookRepo;
import ru.r3is.libka.repos.UserRepo;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepo userRepo;
	private final BookRepo bookRepo;

	public UserServiceImpl(UserRepo userRepo, BookRepo bookRepo) {
		this.userRepo = userRepo;
		this.bookRepo = bookRepo;
	}

	public Long addNew(User user) {
		UserEntity userEntity = userRepo.findByLogin(user.getLogin());
		if (userEntity != null) {
			return userEntity.getId();
		}
		userEntity = new UserEntity();
		userEntity.setLogin(user.getLogin());

		UserEntity savedUserEntity = userRepo.save(userEntity);
		return savedUserEntity.getId();
	}

	public void deleteById(Long userId) {
		userRepo.deleteById(userId);
	}

	public Long addToUserBooks(Long userId, Long bookId) throws NoSuchElementException {
		BookEntity bookEntity = bookRepo.findById(bookId).orElseThrow(NoSuchElementException::new);
		UserEntity userEntity = userRepo.findById(userId).orElseThrow(NoSuchElementException::new);

		userEntity.addReceivedBooks(bookEntity);
		bookEntity.addGivenToUsers(userEntity);

		userRepo.save(userEntity);
		bookRepo.save(bookEntity);

		return bookEntity.getId();
	}

	public List<User> findAll() throws NoSuchElementException {
		List<UserEntity> userEntityList = userRepo.findAll();
		if (userEntityList.isEmpty()) {
			throw new NoSuchElementException("[WARNING] Can't find any user");
		}

		List<User> users = new ArrayList<>();
		for (UserEntity userEntity : userEntityList) {
			Set<Book> books = new HashSet<>();
			for (BookEntity receivedBook : userEntity.getReceivedBooks()) {
				books.add(new Book(receivedBook.getName(), receivedBook.getAuthor().getName()));
			}
			users.add(new User(userEntity.getLogin(), books));
		}
		return users;
	}

	public User findById(Long id) throws NoSuchElementException {
		UserEntity userEntity = userRepo.findById(id).orElseThrow(NoSuchElementException::new);

		Set<Book> books = new HashSet<>();
		for (BookEntity receivedBook : userEntity.getReceivedBooks()) {
			books.add(new Book(receivedBook.getName(), receivedBook.getAuthor().getName()));
		}
		return new User(userEntity.getLogin(), books);
	}

	public User findByLogin(String login) throws NoSuchElementException {
		UserEntity userEntity = userRepo.findByLogin(login);
		if (userEntity == null) {
			throw new NoSuchElementException("[WARNING] User doesn't exist");
		}

		Set<Book> books = new HashSet<>();
		for (BookEntity receivedBook : userEntity.getReceivedBooks()) {
			books.add(new Book(receivedBook.getName(), receivedBook.getAuthor().getName()));
		}
		return new User(userEntity.getLogin(), books);
	}
}

package ru.r3is.libka.services;

import ru.r3is.libka.dto.User;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
	Long addNew(User user);

	Long addToUserBooks(Long userId, Long bookId) throws NoSuchElementException;

	List<User> findAll() throws NoSuchElementException;

	User findById(Long id) throws NoSuchElementException;

	User findByLogin(String login) throws NoSuchElementException;
}

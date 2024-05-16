package ru.r3is.libka.controllers;

import org.slf4j.Logger; //simple logger facade for java
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.r3is.libka.dto.User;
import ru.r3is.libka.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public void addNew(@RequestBody User user) {
		Long newUserId = this.userService.addNew(user);
		log.info("Add new user with id: {}", newUserId);
	}

	@GetMapping("/all")
	public List<User> getAll() {
		return this.userService.findAll();
	}

	//https://users/{id}
	@GetMapping("/{id}")
	public User getById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	//https://users?=login
	@GetMapping
	public User getByLogin(@RequestParam String userLogin) {
		return this.userService.findByLogin(userLogin);
	}

	@PutMapping("/{id}/books")
	public Long addNewBook(@PathVariable Long userId, @RequestParam Long bookId) {
		log.info("Add new book to a user's collection");
//		ResponseEntity<T> полный контроль над статусами и хедерами
		return userService.addToUserBooks(userId, bookId);
	}
}

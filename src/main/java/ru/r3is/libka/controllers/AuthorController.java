package ru.r3is.libka.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.r3is.libka.dto.Author;
import ru.r3is.libka.services.AuthorServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
	private final AuthorServiceImpl authorService;

	public AuthorController(AuthorServiceImpl authorService) {
		this.authorService = authorService;
	}

	@PostMapping
	public void addNew(@RequestBody Author author) {
		Long newAuthorId = this.authorService.addNew(author);
		log.info("Add new author with id: {}", newAuthorId);
	}

	@GetMapping("/all")
	public List<Author> getAll() {
		return this.authorService.findAll();
	}

	@GetMapping("/{id}")
	public Author getById(@PathVariable Long authorId) {
		return this.authorService.findById(authorId);
	}

	@PutMapping("/{id}/books")
	public Long addNewBook(@PathVariable Long authorId, @RequestParam Long bookId) {
		log.info("Add new book to a author's collection");
		return authorService.addToAuthorBooks(authorId, bookId);
	}
}

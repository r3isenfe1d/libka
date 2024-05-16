package ru.r3is.libka.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.r3is.libka.dto.Book;
import ru.r3is.libka.services.BookServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	private final BookServiceImpl bookService;

	public BookController(BookServiceImpl bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public void addNew(@RequestBody Book book) {
		Long newBookId = this.bookService.addNew(book);
		log.info("Add new book with id: {}", newBookId);
	}

	@GetMapping("/all")
	public List<Book> getAll() {
		return this.bookService.findAll();
	}

	@GetMapping("/{id}")
	public Book getById(@PathVariable Long bookId) {
		return this.bookService.findById(bookId);
	}

	@GetMapping
	public Book getByName(@RequestParam String bookName) {
		return this.bookService.findByName(bookName);
	}
}

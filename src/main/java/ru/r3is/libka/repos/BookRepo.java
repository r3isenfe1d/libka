package ru.r3is.libka.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.r3is.libka.entities.BookEntity;

public interface BookRepo extends JpaRepository<BookEntity, Long> {
	BookEntity findByName(String name);
}

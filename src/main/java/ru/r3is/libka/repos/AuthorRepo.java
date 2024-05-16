package ru.r3is.libka.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.r3is.libka.entities.AuthorEntity;

public interface AuthorRepo extends JpaRepository<AuthorEntity, Long> {
	AuthorEntity findByName(String name);
}

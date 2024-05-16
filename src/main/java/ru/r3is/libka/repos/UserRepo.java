package ru.r3is.libka.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.r3is.libka.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
	UserEntity findByLogin(String login);
}

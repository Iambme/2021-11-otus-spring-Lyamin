package ru.otus.lyamin.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lyamin.app.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByName(String userName);
}

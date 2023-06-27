package ru.kata.spring.boot_security.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername (String name);


}

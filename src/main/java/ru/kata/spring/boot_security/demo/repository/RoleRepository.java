package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

public interface RoleRepository extends CrudRepository <Role, Long> {

    Role findByUserRole(String userRole);
}

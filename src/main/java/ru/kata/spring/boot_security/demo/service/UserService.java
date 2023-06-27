package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

//public interface UserService extends UserDetailsService {
    public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserByID(Long id);

    void editUser(User user);

    void deleteUserByID(Long id);


}


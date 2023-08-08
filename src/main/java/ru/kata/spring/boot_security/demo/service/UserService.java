package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    public void saveUser(User user);

    public void updateUser(Long id, User user);

    public void deleteUser(long id);

    public List<User> getAllUsers();

    public User getUserById(long id);

    User getUserByEmail(String email);
}

package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userDao) {
        this.userRepository = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser (User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByID(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(Math.toIntExact(id));
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserByID(Long id) {
        Integer i = (int) (long) id;
        userRepository.deleteAllById(Collections.singleton(i));
    }

}



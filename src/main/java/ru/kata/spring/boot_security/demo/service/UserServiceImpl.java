package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser (User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByID(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById((long) Math.toIntExact(id));
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Transactional
    @Override
    public void editUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserByID(Long id) {
        long i = Math.toIntExact((long) id);
        userRepository.deleteAllById(Collections.singleton(i));
    }

//    @Transactional
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Optional<User> user = userRepository.findByUsername(username);
//
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new UsersDetails(user.get());
//        return null;

//    }
}



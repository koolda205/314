package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    @Lazy
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}

package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UsersDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userDao.getUserByEmail(login));
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return userDao.getUserByEmail(login);
    }
}

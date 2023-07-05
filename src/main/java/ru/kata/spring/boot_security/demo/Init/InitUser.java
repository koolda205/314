package ru.kata.spring.boot_security.demo.Init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class InitUser {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);


        User user = new User("Sergey", "Petrov", 23, "petrov@gmail.com", "admin",
                "admin", adminSet);

        User user1 = new User("Dm", "Ku", 3, "ku@mail.com", "user1",
                "user1", userSet);

        User user2 = new User("Dmitrii", "Kulakov", 33, "kulakov@mail.com", "user",
                "user", userSet);

        userService.saveUser(user);
        userService.saveUser(user2);
        userService.saveUser(user1);
    }
}

package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UsersController {


    private final UserService userService;

    @Autowired
    public UsersController(UserService userServiceImpl, UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {

        model.addAttribute("allUsers", userService.getAllUsers());

        return "users";
    }

    @GetMapping("/admin")
    public String admin(Model model) {


        return "admin";
    }

    @PostMapping("/admin/addNewUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "users";
//        return "redirect:/users";
    }

    @GetMapping("admin/findUsersById")
    public String findUsersById(@RequestParam(value = "id", required = false) Long id,
                                Model model) {

        model.addAttribute("user", userService.getUserByID(id));

        if (userService.getUserByID(id) == null) {
            return "error-page";
        }
            return "user-info";
    }

    @GetMapping("/admin/editUserById")
    public String editUsersById(@RequestParam(value = "id", required = false) Long id, Model model) {

        model.addAttribute("user", userService.getUserByID(id));

        if (userService.getUserByID(id) == null) {
            return "error-page";
        }
        return "edit";
    }

    @PatchMapping("/admin/editUser")
    public String edit(@ModelAttribute("user") User user) {

        userService.editUser(user);

        return "users";
    }

    @GetMapping("/admin/deleteUserById")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {

        if (userService.getUserByID(id) == null) {
            return "error-page";
        }
        userService.deleteUserByID(id);

        return "users";
    }


}
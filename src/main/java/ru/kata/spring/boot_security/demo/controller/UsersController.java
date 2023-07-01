package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "admin";
    }

    @PostMapping("/addNewUser")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/admin";
    }
    @GetMapping("/findUsersById")
    @PreAuthorize("hasAnyAuthority('users:read')")
    public String findUsersById(@RequestParam(value = "id", required = false) Long id,
                                Model model) {

        model.addAttribute("user", userService.getUserByID(id));

        if (userService.getUserByID(id) == null) {
            return "error-page";
        }
            return "user-info";
    }

    @GetMapping("/editUserById")
    @PreAuthorize("hasAnyAuthority('users:read')")
    public String editUsersById(@RequestParam(value = "id", required = false) Long id, Model model) {

        model.addAttribute("user", userService.getUserByID(id));

        if (userService.getUserByID(id) == null) {
            return "error-page";
        }
        return "edit";
    }

    @PatchMapping("/editUser")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String edit(@ModelAttribute("user") User user) {

        userService.editUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/deleteUserById")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {

        if (userService.getUserByID(id) == null) {
            return "error-page";
        }
        userService.deleteUserByID(id);

        return "redirect:/admin";
    }


}
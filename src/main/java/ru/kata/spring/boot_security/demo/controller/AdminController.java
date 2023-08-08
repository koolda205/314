package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAdminPage(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);

        return "admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {

        model.addAttribute("users", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/addNewUser")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           Model model) {

        model.addAttribute("users", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/findUsersById{id}")
    public String findUsersById(@PathVariable("id") Long id,
                                Model model) {

        model.addAttribute("user", userService.getUserById(id));

        if (userService.getUserById(id) == null) {
            return "error-page";
        }
        return "user-info";
    }

    @PatchMapping("/editUser/{id}")
    public String edit(@ModelAttribute("user") @Valid User user,
                       @PathVariable("id") Long id) {

        userService.updateUser(id, user);

        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute("user") @Valid User user,
                             @PathVariable("id") Long id) {

        userService.deleteUser(id);

        return "redirect:/admin";
    }

    @GetMapping("/error-page")
    public String errorPage() {

        return "error-page";
    }

}
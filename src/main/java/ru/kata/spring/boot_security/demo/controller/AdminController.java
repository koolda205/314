package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
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
        model.addAttribute("user", user);
        return "new";
    }

    @PostMapping("/addNewUser")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult, Model model) {

        model.addAttribute("users", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "error-page";
        }

        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/findUsersById{id}")
    public String findUsersById(@PathVariable("id") Long id,
                                Model model) {

        model.addAttribute("user", userService.getUserById(id));

        return "user-info";
    }

    @PatchMapping("/editUser/{id}")
    public String edit(@ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult,
                       @PathVariable("id") Long id) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "error-page";
        }
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
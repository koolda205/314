package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
//import ru.kata.spring.boot_security.demo.util.UsersValidator;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
//    private final UsersValidator usersValidator;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
//        this.usersValidator = usersValidator;
    }


    @GetMapping("/admin")
    public String showAdminPage(@ModelAttribute("user") User user, Model model) {

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
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
                           BindingResult bindingResult, Model model) {

//        usersValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }

        model.addAttribute("users", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/findUsersById{id}")
    public String findUsersById(@PathVariable("id") Long id,
                                Model model) {

        model.addAttribute("user", userService.getUserById(id));

        if (userService.getUserById(id) == null)  {
            return "error-page";
        }
        return "user-info";
    }

    @GetMapping("/{id}/editUserById")
    public String editUsersById(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());

        if (userService.getUserById(id) == null) {
            return "error-page";
        }
        return "edit";
    }

    @PatchMapping("/editUser/{id}")
    public String edit(@ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult,
                       @PathVariable("id") Long id) {

//        usersValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "edit";
        }

        userService.updateUser(id, user);

        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String deleteUsersById(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());

        if (userService.getUserById(id) == null) {
            return "error-page";
        }
        return "delete";
    }

   @DeleteMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @PathVariable("id") Long id) {

        if (userService.getUserById(id) == null) {
            return "error-page";
        }
        userService.deleteUser(id);

        return "redirect:/admin";
    }

}
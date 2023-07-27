package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;


@Controller
@RequestMapping("/")
public class UsersController {
//
//    @GetMapping("login")
//    public String indexPage() {
//        return "/welcome";
//    }

    @GetMapping("user-info")
    public String showUserInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/user-info";
    }


}
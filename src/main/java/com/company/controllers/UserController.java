package com.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.company.service.UserService;
import java.security.Principal;

@Controller
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String pageForUser(Model model, Principal principal) {
        Long id = userService.getUserByMail(principal.getName()).get().getId();
        model.addAttribute(userService.getById(id));
        return "user";
    }
}

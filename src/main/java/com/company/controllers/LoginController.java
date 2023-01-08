package com.company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginRedirectPage() {
        return "/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}

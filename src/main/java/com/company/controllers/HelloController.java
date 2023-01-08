package com.company.controllers;

import com.company.models.User;
import com.company.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.company.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
public class HelloController {

    private final UserService userService;

    private final AdminService adminService;
    @Autowired
    public HelloController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @Value("${hello}")
    private String hello;

    @GetMapping("/hello")
    public String hello() {
        System.out.println(this.hello);
        return "/index/hello";
    }

    @GetMapping("/")
    public String printWelcome(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("welcome");
        model.addAttribute("messages", messages);
        return "/index/index";
    }

    @GetMapping("/users")
    public String userTable(Model model) {
        model.addAttribute("usersList", userService.getUsersList());
        return "/index/users";
    }

    @GetMapping("/users/{id}")
    public String getContactById(Model model, @PathVariable("id") long userId) {
        model.addAttribute("user", userService.getById(userId));
        return "/index/user";
    }

    @GetMapping("/newUser")
    public String addNewUser(@ModelAttribute("user") User user) {
        return "/index/newUser";
    }

    @PostMapping("/newUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/index/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/index/users";
    }

    @GetMapping("/users/{id}/edit")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/index/edit";
    }

    @PatchMapping("/users/{id}/edit")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/index/users";
    }
}

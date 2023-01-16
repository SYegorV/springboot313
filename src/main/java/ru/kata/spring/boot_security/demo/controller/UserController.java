package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.AccessService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final AccessService accessService;
    @Autowired
    public UserController(UserService userService, RoleService roleService, AccessService accessService) {
        this.userService = userService;
        this.roleService = roleService;
        this.accessService = accessService;
    }

    @GetMapping("/")
    public String printWelcome(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("welcome");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String userTable(Model model) {
        model.addAttribute("usersList", userService.getUsersList());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getContactById(Model model, @PathVariable("id") long userId) {
        accessService.doAdminStuff();
        model.addAttribute("user", userService.getById(userId));
        model.addAttribute("roles", roleService.getRoles());
        return "user";
    }

    @GetMapping("/newUser")
    public String addNewUser(@ModelAttribute("user") User user, Model model) {
        accessService.doAdminStuff();
        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("roles", roleService.getRoles());
        return "newUser";
    }

    @PostMapping("/newUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        accessService.doAdminStuff();
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        accessService.doAdminStuff();
        model.addAttribute("usersList", userService.getUsersList());
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "edit";
    }

    @PatchMapping("/users/{id}/edit")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/admin")
    public String adminPage() {
        accessService.doAdminStuff();
        return "admin";
    }
}

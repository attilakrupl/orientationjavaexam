package com.example.orientationexam.controllers;

import com.example.orientationexam.models.User;
import com.example.orientationexam.services.UserService;
import com.example.orientationexam.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String getRoot(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @PostMapping("/users")
    public String postUsers(@RequestParam("name") String name) {
        userService.saveUser(name);
        return "redirect:/";
    }
}

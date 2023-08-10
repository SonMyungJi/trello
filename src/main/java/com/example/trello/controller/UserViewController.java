package com.example.trello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/view/user")
public class UserViewController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";}


    @GetMapping("/signup")
    public String signupPage() { return "signup";}
}
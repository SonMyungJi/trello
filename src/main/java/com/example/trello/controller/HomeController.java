package com.example.trello.controller;

import com.example.trello.jwt.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

//    @GetMapping("/")
//    public String googleLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
//        String token = googleService.googleLogin(code); // 반환 값이 JWT 토큰
//
//        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER,token.substring(7));
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
//        return "redirect:/main";
//    }
}
package com.example.trello.controller;

import com.example.trello.security.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/view/user")
public class UserViewController {

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @GetMapping("/signup")
  public String signupPage() {
    return "signup";
  }

  @GetMapping("/profile")
  public String updatePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails)
      throws JsonProcessingException {

    model.addAttribute("info_username", userDetails.getUser().getUsername());
    model.addAttribute("info_user", userDetails.getUser());
    return "profile";
  }

  @GetMapping("/boards/{boardId}")
  public String homePage(@PathVariable Long boardId) {
    return "readOnly";
  }

  @GetMapping("/boards/{boardId}/edit")
  public String editPage(@PathVariable Long boardId) {
    return "edit"; // edit.html 파일이 위치한 경로로 수정해야 합니다.
  }
}


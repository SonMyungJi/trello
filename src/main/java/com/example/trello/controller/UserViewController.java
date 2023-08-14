package com.example.trello.controller;

import com.example.trello.entity.Board;
import com.example.trello.security.UserDetailsImpl;
import com.example.trello.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/view/user")
@RequiredArgsConstructor
public class UserViewController {

    private final BoardService boardService;

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

    @GetMapping("/boards/{boardId}/edit")
    public String editPage() {
        return "edit";
    }

    @GetMapping("/boards/{boardId}")
    public String readOnlyPage() {
        return "readOnly";
    }
    @GetMapping("/boards")
    public String boardListPage(Model model) {
        List<Board> boardList = boardService.getAllBoards(); // BoardService에서 보드 목록을 가져오는 메소드를 구현해야 함
        model.addAttribute("boardList", boardList);
        return "boardList"; // 보드 목록을 표시하는 HTML 파일의 이름
    }


}
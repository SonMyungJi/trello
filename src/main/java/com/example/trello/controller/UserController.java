package com.example.trello.controller;

import com.example.trello.auth.UserDetailsImpl;
import com.example.trello.dto.*;
import com.example.trello.service.GroupService;
import com.example.trello.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final GroupService groupService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        userService.login(requestDto, response);
        return ResponseEntity.ok().body(new ApiResponseDto("로그인 성공", HttpStatus.CREATED.value()));
    }


    @PostMapping("/group")
    ResponseEntity<ApiResponseDto> createGroup(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        groupService.createGroup(userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("그룹 생성", HttpStatus.OK.value()));
    }
}

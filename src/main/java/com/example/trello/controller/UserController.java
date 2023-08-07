package com.example.trello.controller;

import com.example.trello.dto.ApiResponseDto;
import com.example.trello.dto.LoginRequestDto;
import com.example.trello.dto.SignupRequestDto;
import com.example.trello.jwt.JwtUtil;
import com.example.trello.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<ApiResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("회원가입에 성공했습니다.", HttpStatus.CREATED.value()));
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        userService.login(loginRequestDto, httpServletResponse);
        return ResponseEntity.ok().body(new ApiResponseDto("로그인에 성공하였습니다.", HttpStatus.CREATED.value()));
    }

}

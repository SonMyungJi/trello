package com.example.trello.controller;

import com.example.trello.dto.*;
import com.example.trello.security.UserDetailsImpl;
import com.example.trello.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        return userService.login(requestDto,response);
    }

    @GetMapping("/profile")
    @ResponseBody
    public UserResponseDto lookupUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.lookupUser(userDetails.getUser().getUserId());
    }

    @PutMapping("/profile/{user_id}")
    @ResponseBody
    public ResponseEntity<ApiResponseDto> updateUser(@PathVariable Long user_id, @RequestBody UpdateRequestDto updateRequestDto) {
        return userService.updateUser(user_id, updateRequestDto);
    }

    @DeleteMapping("/profile/{user_id}")
    @ResponseBody
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long user_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.deletePost(user_id, userDetails.getUser());
    }

}

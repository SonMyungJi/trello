package com.example.trello.controller;

import com.example.trello.auth.UserDetailsImpl;
import com.example.trello.dto.ApiResponseDto;
import com.example.trello.dto.CardRequestDto;
import com.example.trello.dto.CardResponseDto;
import com.example.trello.service.GroupService;
import com.example.trello.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final GroupService groupService;

    @PostMapping("/group")
    ResponseEntity<ApiResponseDto> createGroup(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        groupService.createGroup(userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("그룹 생성", HttpStatus.OK.value()));
    }
}

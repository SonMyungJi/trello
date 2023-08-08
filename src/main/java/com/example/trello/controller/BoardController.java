package com.example.trello.controller;

import com.example.trello.dto.ApiResponseDto;
import com.example.trello.dto.InviteRequestDto;
import com.example.trello.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board/{boardId}/user/invite")
    ResponseEntity<ApiResponseDto> inviteMember(@PathVariable Long boardId, InviteRequestDto requestDto) {
        boardService.inviteMember(boardId, requestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("멤버가 초대되었습니다", HttpStatus.OK.value()));
    }
}

package com.example.trello.controller;

import com.example.trello.dto.ApiResponseDto;
import com.example.trello.dto.CommentRequestDto;
import com.example.trello.dto.CommentResponseDto;
import com.example.trello.security.UserDetailsImpl;
import com.example.trello.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/card/{cardId}/comment")
    ResponseEntity<CommentResponseDto> createComment(@PathVariable Long cardId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto result = commentService.createComment(cardId, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/comment/{commentId}")
    ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto result = commentService.updateComment(commentId, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("comment/{commentId}")
    ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("댓글이 삭제되었습니다", HttpStatus.OK.value()));
    }
}

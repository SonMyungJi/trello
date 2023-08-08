package com.example.trello.controller;

import com.example.trello.dto.ApiResponseDto;
import com.example.trello.dto.CardRequestDto;
import com.example.trello.dto.CardResponseDto;
import com.example.trello.security.UserDetailsImpl;
import com.example.trello.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {
    private final CardService cardService;

    @GetMapping("/card/{cardId}")
    ResponseEntity<ApiResponseDto> getCard(@PathVariable Long cardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.getCard(cardId, userDetails.getUser());
        return ResponseEntity.ok().body(new ApiResponseDto("카드 조회", HttpStatus.OK.value()));
    }

    @PostMapping("/column/{columnId}/card")
    ResponseEntity<CardResponseDto> createCard(@PathVariable Long columnId, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto result = cardService.createCard(columnId, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/card/{cardId}")
    ResponseEntity<CardResponseDto> updateCard(@PathVariable Long cardId, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto result = cardService.updateCard(cardId, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("card/{cardId}")
    ResponseEntity<ApiResponseDto> deleteCard(@PathVariable Long cardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.deleteCard(cardId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("카드가 삭제되었습니다", HttpStatus.OK.value()));
    }
}

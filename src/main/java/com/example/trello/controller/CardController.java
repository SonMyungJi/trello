package com.example.trello.controller;

import com.example.trello.dto.ApiResponseDto;
import com.example.trello.dto.CardIndexRequestDto;
import com.example.trello.dto.CardRequestDto;
import com.example.trello.dto.CardResponseDto;
import com.example.trello.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {

  private final CardService cardService;

  @GetMapping("/card/{cardId}")
  ResponseEntity<CardResponseDto> getCard(@PathVariable Long cardId) {
    CardResponseDto result = cardService.getCard(cardId);
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/section/{sectionId}/card")
  ResponseEntity<CardResponseDto> createCard(@PathVariable Long sectionId,
      @RequestBody CardRequestDto requestDto) {
    CardResponseDto result = cardService.createCard(sectionId, requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PutMapping("/card/{cardId}")
  ResponseEntity<CardResponseDto> updateCard(@PathVariable Long cardId,
      @RequestBody CardRequestDto requestDto) {
    CardResponseDto result = cardService.updateCard(cardId, requestDto);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @PutMapping("/card/{cardId}/index")
  ResponseEntity<CardResponseDto> moveCard(@PathVariable Long cardId,
      @RequestBody CardIndexRequestDto requestDto) {
    CardResponseDto result = cardService.moveCard(cardId, requestDto);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @DeleteMapping("/card/{cardId}")
  ResponseEntity<ApiResponseDto> deleteCard(@PathVariable Long cardId) {
    cardService.deleteCard(cardId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ApiResponseDto("카드가 삭제되었습니다", HttpStatus.OK.value()));
  }
}
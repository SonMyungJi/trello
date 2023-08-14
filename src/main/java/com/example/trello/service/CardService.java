package com.example.trello.service;

import com.example.trello.dto.CardIndexRequestDto;
import com.example.trello.dto.CardRequestDto;
import com.example.trello.dto.CardResponseDto;
import com.example.trello.entity.Card;
import com.example.trello.entity.Section;
import com.example.trello.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {

  private final CardRepository cardRepository;
  private final SectionService sectionService;

  public CardResponseDto getCard(Long cardId) {
    Card card = findCard(cardId);
    return new CardResponseDto(card);
  }

  @Transactional
  public CardResponseDto createCard(Long sectionId, CardRequestDto requestDto) {
    Section section = sectionService.findSection(sectionId);

    Card card = cardRepository.save(new Card(section, requestDto));
    return new CardResponseDto(card);
  }

  @Transactional
  public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto) {
    Card card = findCard(cardId);

    card.setCardName(requestDto.getCardName());
    card.setCardDesc(requestDto.getCardDesc());
    card.setNickname(requestDto.getNickname());
    card.setDueDate(requestDto.getDueDate());
    card.setCardColor(requestDto.getCardColor());

    return new CardResponseDto(card);
  }

  public CardResponseDto moveCard(Long cardId, CardIndexRequestDto requestDto) {
    Card card = findCard(cardId);
    Section section = sectionService.findSection(requestDto.getNewSectionId());

    card.setSection(section);
    card.setCardIndex(requestDto.getNewCardIndex());

    cardRepository.save(card);

    return new CardResponseDto(card);
  }

  public void deleteCard(Long cardId) {
    Card card = findCard(cardId);
    cardRepository.delete(card);
  }

  public Card findCard(Long cardId) {
    return cardRepository.findById(cardId).orElseThrow(() ->
            new IllegalArgumentException("해당 카드는 존재하지 않습니다."));
  }
}
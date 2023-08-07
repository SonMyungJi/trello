package com.example.trello.service;

import com.example.trello.dto.CardRequestDto;
import com.example.trello.dto.CardResponseDto;
import com.example.trello.entity.Card;
import com.example.trello.entity.ColumnEntity;
import com.example.trello.entity.User;
import com.example.trello.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public CardResponseDto getCard(Long cardId, User user) {
        Card card = findCard(cardId);
        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto createCard(Long columnId, CardRequestDto requestDto, User user) {
        ColumnEntity columnEntity = columnService.findColumn(columnId);
        Card card = cardRepository.save(new Card(requestDto, user));
        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto, User user) {
        Card card = findCard(cardId);

        if(!(card.getUser().equals(user))) {
            throw new IllegalArgumentException("권한이 없습니다");
        }

        card.setCardName(requestDto.getCardName());
        card.setCardDesc(requestDto.getCardDesc());
        card.setCardColor(requestDto.getCardColor());
        card.setUserId(requestDto.getUserId());
        card.setColumnId(requestDto.getColumnId());

        return new CardResponseDto(card);
    }

    public void deleteCard(Long cardId, User user) {
        Card card = findCard(cardId);

        if (!card.getUser().equals(user)) {
            throw new IllegalArgumentException("권한이 없습니다");
        }
        cardRepository.delete(card);
    }

    public Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new IllegalArgumentException("해당 카드는 존재하지 않습니다."));
    }
}

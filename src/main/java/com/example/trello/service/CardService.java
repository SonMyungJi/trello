package com.example.trello.service;

import com.example.trello.dto.CardRequestDto;
import com.example.trello.dto.CardResponseDto;
import com.example.trello.entity.Board;
import com.example.trello.entity.Card;
import com.example.trello.entity.Columns;
import com.example.trello.entity.User;
import com.example.trello.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final ColumnService columnService;

    public CardResponseDto getCard(Long cardId) {
        Card card = findCard(cardId);

        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto createCard(Long columnsId, CardRequestDto requestDto) {
        Columns columns = columnService.findColumn(columnsId);

        Card card = cardRepository.save(new Card(columns, requestDto));
        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto) {
        Card card = findCard(cardId);

        card.setCardName(requestDto.getCardName());
        card.setCardDesc(requestDto.getCardDesc());
        card.setCardColor(requestDto.getCardColor());
        card.setWorkerId(requestDto.getUserId());

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

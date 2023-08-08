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
    private final GroupService groupService;

    public CardResponseDto getCard(Long cardId, User user) {
        Card card = findCard(cardId);

        // 유저가 그룹의 멤버인지 확인
        if (!groupService.userBelongsToGroup(user, card.getColumns().getBoard().getGroup())) {
            throw new RuntimeException("User is not a member of the group.");
        }

        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto createCard(Long columnsId, CardRequestDto requestDto, User user) {
        Columns columns = columnService.findColumn(columnsId);

        // 유저가 그룹의 멤버인지 확인
        if (!groupService.userBelongsToGroup(user, columns.getBoard().getGroup())) {
            throw new RuntimeException("User is not a member of the group.");
        }

        Card card = cardRepository.save(new Card(columns, requestDto));
        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto, User user) {
        Card card = findCard(cardId);

        // 유저가 그룹의 멤버인지 확인
        if (!groupService.userBelongsToGroup(user, card.getColumns().getBoard().getGroup())) {
            throw new RuntimeException("User is not a member of the group.");
        }

        card.setCardName(requestDto.getCardName());
        card.setCardDesc(requestDto.getCardDesc());
        card.setCardColor(requestDto.getCardColor());
        card.setWorkerId(requestDto.getUserId());

        return new CardResponseDto(card);
    }

    public void deleteCard(Long cardId, User user) {
        Card card = findCard(cardId);

        // 유저가 그룹의 멤버인지 확인
        if (!groupService.userBelongsToGroup(user, card.getColumns().getBoard().getGroup())) {
            throw new RuntimeException("User is not a member of the group.");
        }

        cardRepository.delete(card);
    }

    public Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new IllegalArgumentException("해당 카드는 존재하지 않습니다."));
    }
}

package com.example.trello.dto;

import com.example.trello.entity.Card;
import com.example.trello.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CardResponseDto {

    private String cardName;
    private String cardDesc;
    private String cardColor;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public CardResponseDto(Card card) {
        this.cardName = card.getCardName();
        this.cardDesc = card.getCardDesc();
        this.cardColor = card.getCardColor();
        for (Comment comment : card.getComments()) {
            commentList.add(new CommentResponseDto(comment));
        }
    }
}
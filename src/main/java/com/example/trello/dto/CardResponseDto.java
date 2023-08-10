package com.example.trello.dto;

import com.example.trello.entity.Card;
import com.example.trello.entity.Comment;
import com.example.trello.entity.Section;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CardResponseDto {

  private String cardName;
  private String cardDesc;
  private String cardColor;
  private String nickname;
  private String dueDate;
  private List<CommentResponseDto> commentList = new ArrayList<>();
  private Section section;

  public CardResponseDto(Card card) {
    this.cardName = card.getCardName();
    this.cardDesc = card.getCardDesc();
    this.cardColor = card.getCardColor();
    this.nickname = card.getNickname();
    this.dueDate = card.getDueDate();
    for (Comment comment : card.getComments()) {
      commentList.add(new CommentResponseDto(comment));
    }
    this.section = card.getSection();
  }
}
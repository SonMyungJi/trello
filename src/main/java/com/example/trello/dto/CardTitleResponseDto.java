package com.example.trello.dto;

import com.example.trello.entity.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardTitleResponseDto {

  private String cardName;
  private String nickName;

  public CardTitleResponseDto(Card card) {
    this.cardName = card.getCardName();
    this.nickName = card.getNickname();
  }
}
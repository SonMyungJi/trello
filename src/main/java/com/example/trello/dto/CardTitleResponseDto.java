package com.example.trello.dto;

import com.example.trello.entity.Card;
import com.example.trello.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardTitleResponseDto {

  private String cardName;
//  private User user;

  public CardTitleResponseDto(Card card) {
    this.cardName = card.getCardName();
//    this.user = user.getUser(card.getWorkerId());
  }
}

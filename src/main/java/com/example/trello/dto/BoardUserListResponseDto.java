package com.example.trello.dto;

import com.example.trello.entity.BoardUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUserListResponseDto {

  private String nickname;

  public BoardUserListResponseDto(BoardUser boardUser) {
    this.nickname = boardUser.getUser().getNickname();
  }
}

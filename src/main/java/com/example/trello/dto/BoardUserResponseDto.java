package com.example.trello.dto;

import com.example.trello.entity.Board;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class BoardUserResponseDto {

  private Long boardId; // 보드 번호
  private String boardName; // 보드 이름
  private String boardContents; // 보드 설명
  private Set<UserResponseDto> users; // 유저정보

  public BoardUserResponseDto(Board board) {
    this.boardId = board.getId();
    this.boardName = board.getBoardName();
    this.boardContents = board.getBoardContents();
    this.users = board.getBoardUsers().stream()
        .map(user -> new UserResponseDto(user.getUser()))
        .collect(Collectors.toSet());
  }
}

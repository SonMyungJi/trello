package com.example.trello.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {

  private String boardName; // 보드 이름
  private String boardContents; // 보드 설명
  private String boardColor; // 보드 배경 색상
  private Long userId;
}
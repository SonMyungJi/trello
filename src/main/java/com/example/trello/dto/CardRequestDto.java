package com.example.trello.dto;

import lombok.Getter;

@Getter
public class CardRequestDto {

  private String cardName;
  private String cardDesc;
  private String cardColor;
  private String nickname; // 작업자 할당
  private String dueDate; // 마감일
  private Long sectionId; // 카드 이동
}

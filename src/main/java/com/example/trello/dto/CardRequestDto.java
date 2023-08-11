package com.example.trello.dto;

import lombok.Getter;

@Getter
public class CardRequestDto {

  private String cardName;
  private String cardDesc;
  private String nickname; // 작업자 할당
  private String dueDate; // 마감일
  private String cardColor;
//  private Long sectionId;
}
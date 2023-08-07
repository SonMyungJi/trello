package com.example.trello.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
    private String cardName;
    private String cardDesc;
    private String cardColor;
    private Long userId; // 작업자 할당
    private Long columnId; // 카드 이동
}

package com.example.trello.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardRequestDto {
    private String cardName;
    private String cardDesc;
    private String cardColor;
    private Long userId; // 작업자 할당
    private Long sectionId; // 카드 이동
    private Date dueDate; // 마감일
}

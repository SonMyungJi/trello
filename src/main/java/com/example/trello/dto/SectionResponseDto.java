package com.example.trello.dto;

import com.example.trello.entity.Card;
import com.example.trello.entity.Section;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SectionResponseDto {
    private Long sectionId;
    private String sectionName;
    private List<CardResponseDto> cardList = new ArrayList<>();

    public SectionResponseDto(Section section) {
        this.sectionId = section.getSectionId();
        this.sectionName = section.getSectionName();
        for (Card card : section.getCards()) {
            cardList.add(new CardResponseDto(card));
        }
    }
}

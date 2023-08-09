package com.example.trello.dto;

import com.example.trello.entity.Card;
import com.example.trello.entity.Section;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionResponseDto {

  private Long sectionId;
  private String sectionName;
  private List<CardTitleResponseDto> cardTitleList = new ArrayList<>();

  public SectionResponseDto(Section section) {
    this.sectionId = section.getSectionId();
    this.sectionName = section.getSectionName();
    for (Card card : section.getCards()) {
      cardTitleList.add(new CardTitleResponseDto(card));
    }
  }
}

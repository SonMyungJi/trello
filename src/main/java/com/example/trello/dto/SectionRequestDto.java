package com.example.trello.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SectionRequestDto {

  private String sectionName;
  private Long sectionIndex;
}
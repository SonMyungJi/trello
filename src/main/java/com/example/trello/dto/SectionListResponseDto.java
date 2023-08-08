package com.example.trello.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SectionListResponseDto {
    private List<SectionResponseDto> sectionList;
    public SectionListResponseDto(List<SectionResponseDto> sectionList) {
        this.sectionList = sectionList;
    }
}

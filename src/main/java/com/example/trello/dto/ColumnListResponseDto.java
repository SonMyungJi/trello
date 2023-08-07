package com.example.trello.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ColumnListResponseDto {
    private List<ColumnResponseDto> columnList;

    public ColumnListResponseDto(List<ColumnResponseDto> columnList) {
        this.columnList = columnList;
    }
}

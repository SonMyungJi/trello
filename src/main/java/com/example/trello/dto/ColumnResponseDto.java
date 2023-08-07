package com.example.trello.dto;

import com.example.trello.entity.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnResponseDto {
    private Long columnId;
    private String columnName;
//    private List<CardResponseDto> cards;

    public ColumnResponseDto(Column column) {
        this.columnId = column.getColumnId();
        this.columnName = column.getColumnName();
    }
}

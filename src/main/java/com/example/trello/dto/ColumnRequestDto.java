package com.example.trello.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ColumnRequestDto {
    private String columnName;

    public ColumnRequestDto(String columnName) {
        this.columnName = columnName;
    }
}

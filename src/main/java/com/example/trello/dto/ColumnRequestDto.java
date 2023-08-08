package com.example.trello.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ColumnRequestDto {
    private String columnsName;

    public ColumnRequestDto(String columnsName) {
        this.columnsName = columnsName;
    }
}

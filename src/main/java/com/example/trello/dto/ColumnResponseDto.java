package com.example.trello.dto;

import com.example.trello.entity.Columns;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnResponseDto {
    private Long columnsId;
    private String columnsName;
//    private List<CardResponseDto> columnList = new ArrayList<>();

    public ColumnResponseDto(Columns column) {
        this.columnsId = column.getColumnsId();
        this.columnsName = column.getColumnsName();
    }
}

package com.example.trello.dto;

import com.example.trello.entity.Card;
import com.example.trello.entity.Columns;
import com.example.trello.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ColumnResponseDto {
    private Long columnsId;
    private String columnsName;
    private List<CardResponseDto> cardList = new ArrayList<>();

    public ColumnResponseDto(Columns column) {
        this.columnsId = column.getColumnsId();
        this.columnsName = column.getColumnsName();
        for (Card card : column.getCards()) {
            cardList.add(new CardResponseDto(card));
        }
    }
}

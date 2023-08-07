package com.example.trello.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "column")
@NoArgsConstructor
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "column_id" )
    private Long columnId;

    @jakarta.persistence.Column(name = "column_name", nullable = false, unique = true)
    private String columnName;

//    public List<Cards> getCards() {
//        return cards;
//    }
//
//    public void setCards(List<Cards> cards) {
//        this.cards = cards;
//    }
}

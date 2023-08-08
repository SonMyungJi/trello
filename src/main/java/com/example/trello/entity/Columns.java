package com.example.trello.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "columns")
@NoArgsConstructor
public class Columns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "columns_id" )
    private Long columnsId;

    @Column(name = "columns_name", nullable = false, unique = true)
    private String columnsName;

//    @OneToMany(mappedBy = "cards")
//    private List<cards> cardsList = new ArrayList<>();

//    public List<Cards> getCards() {
//        return cards;
//    }
//
//    public void setCards(List<Cards> cards) {
//        this.cards = cards;
//    }
}

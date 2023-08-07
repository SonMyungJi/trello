package com.example.trello.entity;

import com.example.trello.dto.CardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cardName;

    @Column
    private String cardDesc;

    @Column
    private String cardColor;

    @Column
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "columnId")
    private ColumnEntity column;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Card(ColumnEntity column, CardRequestDto requestDto) {
        this.column = column;
        this.cardName = requestDto.getCardName();
        this.cardDesc = requestDto.getCardDesc();
        this.cardColor = requestDto.getCardColor();
        this.dueDate = requestDto.getDueDate();
    }
}

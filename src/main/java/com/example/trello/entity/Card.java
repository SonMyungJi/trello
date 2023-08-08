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
    private Long workerId;

    @Column
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Card(Board board, CardRequestDto requestDto) {
        this.board = board;
        this.cardName = requestDto.getCardName();
        this.cardDesc = requestDto.getCardDesc();
        this.cardColor = requestDto.getCardColor();
        this.workerId = requestDto.getUserId();
        this.dueDate = requestDto.getDueDate();
    }
}

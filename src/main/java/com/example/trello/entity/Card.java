package com.example.trello.entity;

import com.example.trello.dto.CardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long userId;

    @Column
    private Long columnId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "columnId")
    private ColumnEntity column;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Card(CardRequestDto requestDto, User user) {
        this.cardName = requestDto.getCardName();
        this.cardDesc = requestDto.getCardDesc();
        this.cardColor = requestDto.getCardColor();
        this.userId = requestDto.getUserId();
        this.columnId = requestDto.getColumnId();
        this.user = user;
    }
}

package com.example.trello.entity;

import com.example.trello.dto.CardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JoinColumn(name = "columns_id")
    private Columns columns;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

    public Card(Columns columns, CardRequestDto requestDto) {
        this.columns = columns;
        this.cardName = requestDto.getCardName();
        this.cardDesc = requestDto.getCardDesc();
        this.cardColor = requestDto.getCardColor();
        this.workerId = requestDto.getUserId();
        this.dueDate = requestDto.getDueDate();
    }
}

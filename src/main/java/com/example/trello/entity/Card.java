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
    private Long cardId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionId")
    private Section section;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

    public Card(Section section, CardRequestDto requestDto) {
        this.section = section;
        this.cardName = requestDto.getCardName();
        this.cardDesc = requestDto.getCardDesc();
        this.cardColor = requestDto.getCardColor();
        this.workerId = requestDto.getUserId();
        this.dueDate = requestDto.getDueDate();
    }
}

package com.example.trello.entity;

import com.example.trello.dto.CommentRequestDto;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String body;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cardId")
    private Card card;

    public Comment(Card card, CommentRequestDto requestDto, User user) {
        this.card = card;
        this.body = requestDto.getBody();
        this.user = user;
    }
}

package com.example.trello.entity;

import com.example.trello.dto.CommentRequestDto;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    public Comment(Board board, CommentRequestDto requestDto, User user) {
        this.board = board;
        this.body = requestDto.getBody();
        this.user = user;
    }
}

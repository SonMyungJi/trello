package com.example.trello.entity;


import com.example.trello.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
public class Board extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // board_id

    @Column(nullable = false)
    private String boardName; // 보드 이름



    @Column(nullable = false)
    private String boardContents; // 보드 설명

    @Column(nullable = false)
    private String boardColor; // 보드 배경 색상

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Board(BoardRequestDto boardRequestDto, User user){
        this.boardName = boardRequestDto.getBoardName();
        this.boardContents = boardRequestDto.getBoardContents();
        this.boardColor = boardRequestDto.getBoardContents();
        this.user = user;
    }

    public void update(BoardRequestDto boardRequestDto){
        this.boardName = boardRequestDto.getBoardName();
        this.boardContents = boardRequestDto.getBoardContents();
        this.boardColor = boardRequestDto.getBoardContents();
    }




}

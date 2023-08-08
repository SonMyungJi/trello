package com.example.trello.entity;


import com.example.trello.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
public class Board extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 보드 번호

    @Column(nullable = false)
    private String boardName; // 보드 이름

    @Column(nullable = false)
    private String boardContents; // 보드 설명

    @Column(nullable = false)
    private String boardColor; // 보드 배경 색상

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardUser> boardUsers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User creator; // 보드 생성자


    public Board(BoardRequestDto boardRequestDto, User user){
        this.boardName = boardRequestDto.getBoardName();
        this.boardContents = boardRequestDto.getBoardContents();
        this.boardColor = boardRequestDto.getBoardColor();
        this.creator = user;
    }

    public void addBoardUsers(BoardUser boardUser){
        this.boardUsers.add(boardUser);
    }


    public void update(BoardRequestDto boardRequestDto){
        this.boardName = boardRequestDto.getBoardName();
        this.boardContents = boardRequestDto.getBoardContents();
        this.boardColor = boardRequestDto.getBoardColor();
    }




}

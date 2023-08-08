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
    private Long id; // 보드 번호

    @Column(nullable = false)
    private String boardName; // 보드 이름

    @Column(nullable = false)
    private String boardContents; // 보드 설명

    @Column(nullable = false)
    private String boardColor; // 보드 배경 색상

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator; // 보드 생성자

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;



//    그룹
//    보드1 보드2 보드3



//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "board_users",
//            joinColumns = @JoinColumn(name = "board_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> users = new HashSet<>(); // 보드에 포함된 유저목록

    public Board(BoardRequestDto boardRequestDto, User user, GroupEntity group){
        this.boardName = boardRequestDto.getBoardName();
        this.boardContents = boardRequestDto.getBoardContents();
        this.boardColor = boardRequestDto.getBoardColor();
        this.creator = user;
        this.group = group;
    }

    public void update(BoardRequestDto boardRequestDto){
        this.boardName = boardRequestDto.getBoardName();
        this.boardContents = boardRequestDto.getBoardContents();
        this.boardColor = boardRequestDto.getBoardColor();
    }




}

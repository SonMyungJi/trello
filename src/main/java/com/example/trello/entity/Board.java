package com.example.trello.entity;


import com.example.trello.dto.BoardRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
public class Board extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // 보드 번호

  @Column(nullable = false)
  private String boardName; // 보드 이름

  @Column(nullable = true)
  private String boardContents; // 보드 설명

  @Column(nullable = true)
  private String boardColor; // 보드 배경 색상

  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BoardUser> boardUsers = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", nullable = false)
  private User creator; // 보드 생성자

  @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  private Set<Section> sections = new HashSet<>();

  public Board(BoardRequestDto boardRequestDto, User user) {
    this.boardName = boardRequestDto.getBoardName();
    this.boardContents = boardRequestDto.getBoardContents();
    this.boardColor = boardRequestDto.getBoardColor();
    this.creator = user;
  }

  public void addBoardUsers(BoardUser boardUser) {
    this.boardUsers.add(boardUser);
  }


  public void update(BoardRequestDto boardRequestDto) {
    this.boardName = boardRequestDto.getBoardName();
    this.boardContents = boardRequestDto.getBoardContents();
    this.boardColor = boardRequestDto.getBoardColor();
  }


}
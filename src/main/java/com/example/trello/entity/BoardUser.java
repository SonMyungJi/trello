package com.example.trello.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "boardUsers")
public class BoardUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "boardId", nullable = false)
  private Board board;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private BoardUserRoleEnum role;

  public BoardUser(User user, Board board, BoardUserRoleEnum role) {
    this.user = user;
    this.board = board;
    this.role = role;
  }
}
package com.example.trello.entity;

import com.example.trello.dto.CardRequestDto;
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
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long cardId;

  @Column(nullable = false)
  private String cardName;

  @Column(nullable = true)
  private String cardDesc;

  @Column(nullable = true)
  private String cardColor;

  @Column(nullable = true)
  private String nickname;

  @Column(nullable = true)
  private String dueDate;

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
    this.nickname = requestDto.getNickname();
    this.dueDate = requestDto.getDueDate();
  }
}
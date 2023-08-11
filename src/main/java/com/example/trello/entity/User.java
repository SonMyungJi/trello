package com.example.trello.entity;

import com.example.trello.dto.UpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "nickname")
  private String nickname;

  private Long kakaoId;

  private String googleId;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<BoardUser> boardUsers;

  public User(String username, String password, String nickname) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
  }

  public User(String username, String password, String nickname, Long kakaoId) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.kakaoId = kakaoId;
  }

  public User(String username, String password, String nickname, String googleId) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.googleId = googleId;
  }

  public User kakaoIdUpdate(Long kakaoId) {
    this.kakaoId = kakaoId;
    return this;
  }

  public User googleIdUpdate(String googleId) {
    this.googleId = googleId;
    return this;
  }

  public void update(UpdateRequestDto updateRequestDto, String password) {
    this.nickname = updateRequestDto.getNickname();
    this.password = password;
  }
}
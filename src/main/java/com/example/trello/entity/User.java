package com.example.trello.entity;

import com.example.trello.dto.UpdateRequestDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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

    @Column(name = "nickname", nullable = false)
    private String nickname;

    private Long kakaoId;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<BoardUser> boardUsers;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public User(String username, String password,String nickname, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.kakaoId =kakaoId;
    }

    public User getUser(Long userId) {
        if (this.userId.equals(userId)) {
            return this;
        } else {
            return null; // 예시로 userId가 일치하지 않을 경우 null을 반환합니다.
        }
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

    public void update(UpdateRequestDto updateRequestDto, String password) {
        this.nickname = updateRequestDto.getNickname();
        this.password = password;
    }
}

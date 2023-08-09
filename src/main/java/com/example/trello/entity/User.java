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

    @Column(nullable = false, unique = true)
    private String email;
    private Long kakaoId;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<BoardUser> boardUsers;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public void update(UpdateRequestDto updateRequestDto, String password) {
        this.nickname = updateRequestDto.getNickname();
        this.password = password;
    }

    public User(String username, String password, String email, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId =kakaoId;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }
}

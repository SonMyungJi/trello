package com.example.trello.entity;

import com.example.trello.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String nickname;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserGroup> userGroups = new HashSet<>();

    public User(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }
}

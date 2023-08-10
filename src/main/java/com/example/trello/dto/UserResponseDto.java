package com.example.trello.dto;

import com.example.trello.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long userId;
    private String username;
    private String nickname;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}
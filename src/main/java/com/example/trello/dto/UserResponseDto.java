package com.example.trello.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String username;

    public UserResponseDto(Long id, String username){
        this.id = id;
        this.username = username;
    }
}

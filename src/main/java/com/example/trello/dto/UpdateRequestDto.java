package com.example.trello.dto;
import lombok.Getter;

@Getter
public class UpdateRequestDto {
    private String nickname;
    private String password;
    private String checkPassword;
}

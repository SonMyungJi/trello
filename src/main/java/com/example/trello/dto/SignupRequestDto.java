package com.example.trello.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String nickname;
    private String password;
}

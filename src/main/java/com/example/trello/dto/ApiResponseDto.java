package com.example.trello.dto;

public class ApiResponseDto {
    private String msg;
    private Integer statusCode;

    public ApiResponseDto (String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}

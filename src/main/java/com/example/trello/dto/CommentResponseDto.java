package com.example.trello.dto;

import com.example.trello.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private String body;
    private String nickname;

    public CommentResponseDto(Comment comment) {
        this.body = comment.getBody();
        this.nickname = comment.getUser().getNickname();
    }
}

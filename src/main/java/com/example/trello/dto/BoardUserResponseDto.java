package com.example.trello.dto;

import com.example.trello.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BoardUserResponseDto {
    private Long boardId; // 보드 번호
    private String boardName; // 보드 이름
    private String boardContents; // 보드 설명
    private LocalDateTime created; // 생성시간
    private Set<UserResponseDto> users; // 유저정보

    public BoardUserResponseDto(Board board){
        this.boardId = board.getId();
        this.boardName = board.getBoardName();
        this.boardContents = board.getBoardContents();
        this.created = board.getCreatedAt();
        this.users = board.getBoardUsers().stream()
                .map(user -> new UserResponseDto(user.getUser()))
                .collect(Collectors.toSet());
    }
}

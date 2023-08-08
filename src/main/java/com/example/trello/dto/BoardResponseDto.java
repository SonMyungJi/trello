package com.example.trello.dto;

import com.example.trello.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private Long id; // 보드 번호
    private Long groupid; // 보드가 속한 그룹 번호
    private String boardName; // 보드 이름
    private String boardContents; // 보드 설명
    private String boardColor; // 보드 배경 색상
    private LocalDateTime created; // 생성시간
    private LocalDateTime modifiedAt; // 수정시간

    public BoardResponseDto(Board board, Long groupid){
        this.id = board.getId();
        this.groupid = groupid;
        this.boardName = board.getBoardName();
        this.boardContents = board.getBoardContents();
        this.boardColor = board.getBoardColor();
        this.created = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}

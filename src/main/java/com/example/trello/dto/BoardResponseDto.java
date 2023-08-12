package com.example.trello.dto;

import com.example.trello.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BoardResponseDto {

    private Long id; // 보드 번호
    private String boardName; // 보드 이름
    //  private String boardContents; // 보드 설명
//  private String boardColor; // 보드 배경 색상
    private LocalDateTime createdAt; // 생성시간
    private LocalDateTime modifiedAt; // 수정시간
    private List<SectionResponseDto> sectionResponseDtos; // 보드에 연결된 컬럼리스트

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.boardName = board.getBoardName();
//    this.boardContents = board.getBoardContents();
//    this.boardColor = board.getBoardColor();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.sectionResponseDtos = board.getSections().stream()
                .map(SectionResponseDto::new)
                .collect(Collectors.toList());
    }
}
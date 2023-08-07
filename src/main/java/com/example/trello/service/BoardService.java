package com.example.trello.service;

import com.example.trello.dto.BoardRequestDto;
import com.example.trello.dto.BoardResponseDto;
import com.example.trello.entity.User;


public interface BoardService {
    /**
     * 보드 생성
     * @param boardRequestDto 보드 정보
     * @param user 보드 생성자
     * @return 보드 생성 결과
     */
    BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user);


    /**
     * 보드 수정
     * @param id 수정할 보드번호
     * @param boardRequestDto 수정할 보드 내용
     * @param user 수정 요청자
     * @return 수정된 보드 정보
     */
    BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto,  User user);

    /**
     * 보드 삭제
     * @param id 삭제할 보드번호
     * @param user 삭제 요청자
     */
    void deleteBoard(Long id, User user);

    /**
     * 보드 초대
     * @param boardid 초대할 보드번호
     * @param userid 초대받을 유저번호
     * @param user 초대 요청자
     */
    void inviteBoard(Long boardid, Long userid, User user);

}

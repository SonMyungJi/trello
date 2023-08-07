package com.example.trello.service;

import com.example.trello.dto.BoardRequestDto;
import com.example.trello.dto.BoardResponseDto;
import com.example.trello.dto.BoardUserResponseDto;
import com.example.trello.entity.User;

import java.util.List;


public interface BoardService {

    /**
     * 보드 전체조회
     * @return 보드 전체목록 + 정보
     */
    List<BoardResponseDto> getBoards();

    /**
     * 보드 개별조회
     * @param id 보드 번호
     * @return 보드 정보
     */

    BoardResponseDto getBoard(Long id);

    /**
     * 보드 유저 조회 (전체보드)
     * @return 보드 정보 + 유저 정보 (전체)
     */
    List<BoardUserResponseDto> getBoardsUser();

    /**
     * 보드 유저 조회 (선택보드)
     * @param id 보드 번호
     * @return 보드 정보 + 유저 정보 (선택)
     */

    BoardUserResponseDto getBoardUser(Long id);


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

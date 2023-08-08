package com.example.trello.controller;


import com.example.trello.dto.ApiResponseDto;
import com.example.trello.dto.BoardRequestDto;
import com.example.trello.dto.BoardResponseDto;
import com.example.trello.dto.BoardUserResponseDto;
import com.example.trello.security.UserDetailsImpl;
import com.example.trello.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 보드 전체조회
    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponseDto>> getBoards(@RequestParam Long groupid){
        List<BoardResponseDto> boardResponseDtos = boardService.getBoards(groupid);
        return ResponseEntity.ok().body(boardResponseDtos);
    }

    // 보드 개별조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id,
                                                     @RequestParam Long groupid){
        BoardResponseDto boardResponseDto = boardService.getBoard(id, groupid);
        return ResponseEntity.ok().body(boardResponseDto);
    }

//    // 보드 유저 조회 (전체보드)
//    @GetMapping("/boards/user")
//    public ResponseEntity<List<BoardUserResponseDto>> getBoardsUser(){
//        List<BoardUserResponseDto> boardUserResponseDtos = boardService.getBoardsUser();
//        return ResponseEntity.ok().body(boardUserResponseDtos);
//    }
//
//    // 보드 유저 조회 (선택보드)
//    @GetMapping("/boards/user/{id}")
//    public ResponseEntity<BoardUserResponseDto> getBoardUser(@PathVariable Long id){
//        BoardUserResponseDto boardUserResponseDto = boardService.getBoardUser(id);
//        return ResponseEntity.ok().body(boardUserResponseDto);
//    }

    // 보드 생성
    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto boardRequestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestParam Long groupid){
        BoardResponseDto boardResponseDto = boardService.createBoard(boardRequestDto, userDetails.getUser(), groupid);

        return ResponseEntity.ok().body(boardResponseDto);
    }

    // 보드 수정
    @PutMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id,
                                                        @RequestBody BoardRequestDto boardRequestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestParam Long groupid){

        BoardResponseDto boardResponseDto = boardService.updateBoard(id, boardRequestDto, userDetails.getUser(), groupid);

        return ResponseEntity.ok().body(boardResponseDto);
    }

    // 보드 삭제
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<ApiResponseDto> deleteBoard(@PathVariable Long id,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        try{
            boardService.deleteBoard(id, userDetails.getUser());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.ok().body(new ApiResponseDto("보드를 삭제하였습니다.", HttpStatus.OK.value()));
    }

    // 보드 초대
    @PostMapping("/boards/{boardid}/invite/{userid}")
    public ResponseEntity<ApiResponseDto> inviteBoard(@PathVariable Long boardid,
                                                      @PathVariable Long userid,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        try{
            boardService.inviteBoard(boardid, userid, userDetails.getUser());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.ok().body(new ApiResponseDto("유저를 초대하였습니다.", HttpStatus.OK.value()));
    }
}

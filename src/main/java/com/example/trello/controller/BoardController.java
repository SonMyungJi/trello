package com.example.trello.controller;


import com.example.trello.dto.ApiResponseDto;
import com.example.trello.dto.BoardRequestDto;
import com.example.trello.dto.BoardResponseDto;
import com.example.trello.dto.BoardUserResponseDto;
import com.example.trello.security.UserDetailsImpl;
import com.example.trello.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  // 보드 전체조회
  @GetMapping("/boards")
  public ResponseEntity<List<BoardResponseDto>> getBoards() {
    List<BoardResponseDto> boardResponseDtos = boardService.getBoards();
    return ResponseEntity.ok().body(boardResponseDtos);
  }

  // 보드 개별조회
  @GetMapping("/boards/{boardId}")
  public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
    BoardResponseDto boardResponseDto = boardService.getBoard(boardId);
    return ResponseEntity.ok().body(boardResponseDto);
  }

  // 보드 유저 조회 (전체보드)
  @GetMapping("/boards/user")
  public ResponseEntity<List<BoardUserResponseDto>> getBoardsUser() {
    List<BoardUserResponseDto> boardUserResponseDtos = boardService.getBoardsUser();
    return ResponseEntity.ok().body(boardUserResponseDtos);
  }

  // 보드 유저 조회 (선택보드)
  @GetMapping("/boards/user/{userId}")
  public ResponseEntity<BoardUserResponseDto> getBoardUser(@PathVariable Long userId) {
    BoardUserResponseDto boardUserResponseDto = boardService.getBoardUser(userId);
    return ResponseEntity.ok().body(boardUserResponseDto);
  }

  @GetMapping("/boards/{boardId}/users")
  public ResponseEntity<List<String>> getSuggestions(@PathVariable Long boardId) {
    List<String> boardUsers = boardService.getSuggestions(boardId);
    return ResponseEntity.ok().body(boardUsers);
  }

  // 보드 생성
  @PostMapping("/boards")
  public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto boardRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    BoardResponseDto boardResponseDto = boardService.createBoard(boardRequestDto,
            userDetails.getUser());

    return ResponseEntity.ok().body(boardResponseDto);
  }

  // 보드 수정
  @PutMapping("/boards/{boardId}")
  public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long boardId,
                                                      @RequestBody BoardRequestDto boardRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    BoardResponseDto boardResponseDto = boardService.updateBoard(boardId, boardRequestDto,
            userDetails.getUser());

    return ResponseEntity.ok().body(boardResponseDto);
  }

  // 보드 삭제
  @DeleteMapping("/boards/{boardId}")
  public ResponseEntity<ApiResponseDto> deleteBoard(@PathVariable Long boardId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
    try {
      boardService.deleteBoard(boardId, userDetails.getUser());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest()
              .body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    return ResponseEntity.ok().body(new ApiResponseDto("보드를 삭제하였습니다.", HttpStatus.OK.value()));
  }

  // 보드 초대
  @PostMapping("/boards/{boardId}/invite/{userId}")
  public ResponseEntity<ApiResponseDto> inviteBoard(@PathVariable Long boardId,
                                                    @PathVariable Long userId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
    try {
      boardService.inviteBoard(boardId, userId, userDetails.getUser());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest()
              .body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    return ResponseEntity.ok().body(new ApiResponseDto("유저를 초대하였습니다.", HttpStatus.OK.value()));
  }
}
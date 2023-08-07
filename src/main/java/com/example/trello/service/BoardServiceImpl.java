package com.example.trello.service;

import com.example.trello.dto.BoardRequestDto;
import com.example.trello.dto.BoardResponseDto;
import com.example.trello.entity.Board;
import com.example.trello.entity.User;
import com.example.trello.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user){
        if(user == null){
            throw new IllegalArgumentException("로그인 후 시도해주세요.");
        }

        Board board = new Board(boardRequestDto, user);
        boardRepository.save(board);

        return new BoardResponseDto(board);
    }


    @Override
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto,  User user){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정할 보드가 존재하지 않습니다.")
        );

        if(board.getUser().getId().equals(user.getId())){
            board.update(boardRequestDto);
        } else {
            throw new IllegalArgumentException("보드 생성자만 수정이 가능합니다.");
        }

        return new BoardResponseDto(board);
    }


    @Override
    public void deleteBoard(Long id, User user){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제할 보드가 존재하지 않습니다.")
        );

        if(board.getUser().getId().equals(user.getId())){
            boardRepository.delete(board);
        } else {
            throw new IllegalArgumentException("보드 생성자만 삭제가 가능합니다.");
        }
    }

    @Override
    public void inviteBoard(Long boardid, Long userid, User user){

    }
}

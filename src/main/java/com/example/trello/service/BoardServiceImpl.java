package com.example.trello.service;

import com.example.trello.dto.BoardRequestDto;
import com.example.trello.dto.BoardResponseDto;
import com.example.trello.entity.Board;
import com.example.trello.entity.User;
import com.example.trello.repository.BoardRepository;
import com.example.trello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

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
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto,  User user){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정할 보드가 존재하지 않습니다.")
        );

        if(board.getCreator().getId().equals(user.getId())){
            board.update(boardRequestDto);
        } else {
            throw new IllegalArgumentException("보드 생성자만 수정이 가능합니다.");
        }

        return new BoardResponseDto(board);
    }


    @Override
    @Transactional
    public void deleteBoard(Long id, User user){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제할 보드가 존재하지 않습니다.")
        );

        if(board.getCreator().getId().equals(user.getId())){
            boardRepository.delete(board);
        } else {
            throw new IllegalArgumentException("보드 생성자만 삭제가 가능합니다.");
        }
    }

    @Override
    @Transactional
    public void inviteBoard(Long boardid, Long userid, User user){
        Board board = boardRepository.findById(boardid).orElseThrow(
                () -> new IllegalArgumentException("초대할 보드가 존재하지 않습니다.")
        );

        if(!board.getCreator().getId().equals(user.getId())){
            throw new IllegalArgumentException("보드 생성자만 초대할 수 있습니다.");
        }

        User invitedUser = userRepository.findById(userid).orElseThrow(
                () -> new IllegalArgumentException("초대받을 유저가 존재하지 않습니다.")
        );

        if(board.getUsers().contains(invitedUser)){
            throw new IllegalArgumentException("이미 보드에 포함된 유저입니다.");
        } else {
            board.getUsers().add(invitedUser);
            boardRepository.save(board);
        }

    }
}

package com.example.trello.service;

import com.example.trello.dto.BoardRequestDto;
import com.example.trello.dto.BoardResponseDto;
import com.example.trello.dto.BoardUserResponseDto;
import com.example.trello.entity.Board;
import com.example.trello.entity.GroupEntity;
import com.example.trello.entity.User;
import com.example.trello.repository.BoardRepository;
import com.example.trello.repository.GroupRepository;
import com.example.trello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<BoardResponseDto> getBoards(Long groupid){
        List<Board> boards = boardRepository.findAll();
        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        for (Board board : boards){
            boardResponseDtos.add(new BoardResponseDto(board, groupid));
        }

        return boardResponseDtos;
    }

    @Override
    public BoardResponseDto getBoard(Long id, Long groupid){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회할 보드가 존재하지 않습니다.")
        );

        return new BoardResponseDto(board, groupid);
    }

//    @Override
//    public List<BoardUserResponseDto> getBoardsUser(){
//        List<Board> boards = boardRepository.findAll();
//        List<BoardUserResponseDto> boardUserResponseDtos = new ArrayList<>();
//
//        for (Board board : boards){
//            boardUserResponseDtos.add(new BoardUserResponseDto(board));
//        }
//
//        return boardUserResponseDtos;
//    }
//
//    @Override
//    public BoardUserResponseDto getBoardUser(Long id){
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("조회할 보드가 존재하지 않습니다.")
//        );
//
//        return new BoardUserResponseDto(board);
//    }

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user, Long groupid){
        if(user == null){
            throw new IllegalArgumentException("로그인 후 시도해주세요.");
        }

        GroupEntity group = groupRepository.findById(groupid).orElseThrow(
                () -> new IllegalArgumentException("그룹이 존재하지 않습니다.")
        );

        Board board = new Board(boardRequestDto, user, group);
        boardRepository.save(board);

        return new BoardResponseDto(board, groupid);
    }


    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto,  User user, Long groupid){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정할 보드가 존재하지 않습니다.")
        );

        if(board.getCreator().getId().equals(user.getId())){
            board.update(boardRequestDto);
        } else {
            throw new IllegalArgumentException("보드 생성자만 수정이 가능합니다.");
        }

        return new BoardResponseDto(board, groupid);
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

//        if(board.getUsers().contains(invitedUser)){
//            throw new IllegalArgumentException("이미 보드에 포함된 유저입니다.");
//        } else {
//            board.getUsers().add(invitedUser);
//            boardRepository.save(board);
//        }

    }
}

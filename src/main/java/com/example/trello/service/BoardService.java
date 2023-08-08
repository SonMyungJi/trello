package com.example.trello.service;

import com.example.trello.dto.InviteRequestDto;
import com.example.trello.entity.Board;
import com.example.trello.entity.User;
import com.example.trello.entity.UserGroup;
import com.example.trello.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;

    public void inviteMember(Long boardId, InviteRequestDto requestDto) {
        Board board = findBoard(boardId);
        User user = userService.findUser(requestDto.getNickname());

        // 유저를 그룹에 추가하기 위해 UserGroup 엔티티 생성
        UserGroup newUserGroup = new UserGroup();
        newUserGroup.setUser(user);
        newUserGroup.setGroup(board.getGroup());

        board.getGroup().getUserGroups().add(new UserGroup());
    }

    public Board findBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당 칼럼는 존재하지 않습니다."));
    }
}

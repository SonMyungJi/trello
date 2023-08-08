package com.example.trello.service;

import com.example.trello.dto.InviteRequestDto;
import com.example.trello.entity.*;
import com.example.trello.repository.BoardRepository;
import com.example.trello.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final GroupService groupService;

    public void createBoard(Long groupId) {
        GroupEntity group = groupService.findGroup(groupId);
        Board board = boardRepository.save(new Board(group));
    }

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

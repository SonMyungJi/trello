package com.example.trello.service;

import com.example.trello.dto.CommentRequestDto;
import com.example.trello.dto.CommentResponseDto;
import com.example.trello.entity.Board;
import com.example.trello.entity.Comment;
import com.example.trello.entity.User;
import com.example.trello.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardService boardService;
    private final GroupService groupService;

    @Transactional
    public CommentResponseDto createComment(Long boardId, CommentRequestDto requestDto, User user) {
        Board board = boardService.findBoard(boardId);

        // 유저가 그룹의 멤버인지 확인
        if (!groupService.userBelongsToGroup(user, board.getGroup())) {
            throw new RuntimeException("User is not a member of the group.");
        }

        Comment comment= commentRepository.save(new Comment(board, requestDto, user));
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(commentId);

        // 유저가 그룹의 멤버인지 확인
        if (!groupService.userBelongsToGroup(user, comment.getBoard().getGroup())) {
            throw new RuntimeException("User is not a member of the group.");
        }

        if(!(comment.getUser().equals(user))) {
            throw new IllegalArgumentException("권한이 없습니다");
        }

        comment.setBody(requestDto.getBody());

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);

        // 유저가 그룹의 멤버인지 확인
        if (!groupService.userBelongsToGroup(user, comment.getBoard().getGroup())) {
            throw new RuntimeException("User is not a member of the group.");
        }

        if (!comment.getUser().equals(user)) {
            throw new IllegalArgumentException("권한이 없습니다");
        }
        commentRepository.delete(comment);
    }

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("해당 카드는 존재하지 않습니다."));
    }
}

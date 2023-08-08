package com.example.trello.service;

import com.example.trello.dto.CommentRequestDto;
import com.example.trello.dto.CommentResponseDto;
import com.example.trello.entity.Card;
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
    private final CardService cardService;

    @Transactional
    public CommentResponseDto createComment(Long cardId, CommentRequestDto requestDto, User user) {
        Card card = cardService.findCard(cardId);
        Comment comment= commentRepository.save(new Comment(card, requestDto, user));
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(commentId);

        if(!(comment.getUser().equals(user))) {
            throw new IllegalArgumentException("권한이 없습니다");
        }

        comment.setBody(requestDto.getBody());

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);

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

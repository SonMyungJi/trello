package com.example.trello.service;

import com.example.trello.entity.Board;
import com.example.trello.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board findBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당 칼럼는 존재하지 않습니다."));
    }
}

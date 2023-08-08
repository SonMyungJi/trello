package com.example.trello.service;

import com.example.trello.entity.ColumnEntity;
import com.example.trello.repository.ColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;

    public ColumnEntity findColumn(Long columnId) {
        return columnRepository.findById(columnId).orElseThrow(() ->
                new IllegalArgumentException("해당 칼럼는 존재하지 않습니다."));
    }
}
package com.example.trello.service;

import com.example.trello.entity.Column;
import com.example.trello.repository.ColumnRepository;
import org.springframework.stereotype.Service;

@Service
public class ColumnService {

    private ColumnRepository columnRepository;

    //컬럼 조회
    public Column findByColumnWithCard(Long id) {
        return columnRepository.findById(id).orElse(null);
    }

    //컬럼 생성
    public Column createColumn(String columnName) {
        Column column = new Column();
        column.setColumnName(columnName);
        return columnRepository.save(column);
    }

    //컬럼 이름 수정
    public Column updateColumnName(Long id, String newName) {
        Column column = columnRepository.findById(id).orElse(null);
        if (column != null) {
            column.setColumnName(newName);
            return columnRepository.save(column);
        }
        return null;
    }

    //컬럼 이동


    //컬럼 삭제
    public void columnDelete(Long id) {
        columnRepository.deleteById(id);
    }



}

package com.example.trello.service;

import com.example.trello.dto.ColumnListResponseDto;
import com.example.trello.dto.ColumnRequestDto;
import com.example.trello.dto.ColumnResponseDto;
import com.example.trello.entity.Columns;
import com.example.trello.repository.ColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;

    //컬럼 조회
    public ColumnListResponseDto findByColumnWithCard() {
        List<ColumnResponseDto> columnList = columnRepository.findAll().stream()
                .map(ColumnResponseDto::new)
                .collect(Collectors.toList());

        return new ColumnListResponseDto(columnList);
    }

    //컬럼 생성
    public ColumnResponseDto createColumn(ColumnRequestDto requestDto) {
        Columns columns = new Columns(requestDto);
//        columns.setColumnsName(requestDto);
        return new ColumnResponseDto(columnRepository.save(columns));
    }

    //컬럼 이름 수정
    public ColumnResponseDto updateColumnName(Long id, ColumnRequestDto requestDto) {
        Columns columns = columnRepository.findById(id).orElse(null);
        if (columns != null) {
            columns.setColumnsName(requestDto);
            columnRepository.save(columns);
            return new ColumnResponseDto(columns);
        }
        return null;
    }

    //컬럼 이동
    //    public ColumnResponseDto moveColumn(Long columnId, int newPosition) {
//        Column column = columnRepository.findById(columnId).orElse(null);
//        if (column != null) {
//            List<Column> allColumns = columnRepository.findAll();
//            int maxPosition = allColumns.size() - 1;
//            newPosition = Math.min(Math.max(newPosition, 0), maxPosition);
//            allColumns.remove(column);
//            allColumns.add(newPosition, column);
//            for (int i = 0; i < allColumns.size(); i++) {
//                allColumns.get(i).setPosition(i); // Update the positions of all columns
//            }
//            return ColumnRepository.save(allColumns).get(newPosition);
//        }
//        return null;
//    }

    //컬럼 삭제
    public String columnDelete(Long id) {
        columnRepository.deleteById(id);
        return null;
    }



}

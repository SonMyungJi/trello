package com.example.trello.controller;

import com.example.trello.dto.ColumnListResponseDto;
import com.example.trello.dto.ColumnResponseDto;
import com.example.trello.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequiredArgsConstructor
public class ColumnContoller {

    private final ColumnService columnService;

    //컬럼 조회
    @GetMapping("/column")
    public ResponseEntity<ColumnListResponseDto> findByColumnWithCard() {
        ColumnListResponseDto result = columnService.findByColumnWithCard();
            return ResponseEntity.ok().body(result);
        }

    //컬럼 생성
    @PostMapping("/column")
    public ResponseEntity<ColumnResponseDto> createColumn(@RequestBody String columnName) {
        ColumnResponseDto createColumn = columnService.createColumn(columnName);
        return ResponseEntity.status(HttpStatus.CREATED).body(createColumn);
    }

    //컬럼 이름 수정
    @PutMapping("/column/{coulmn id}")
    public ResponseEntity<ColumnResponseDto> updateColumnName(@PathVariable Long id, @RequestBody String newName) {
        try {
            ColumnResponseDto result = columnService.updateColumnName(id, newName);
            return ResponseEntity.ok().body(result);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    //컬럼 삭제
    @DeleteMapping("/column/{coulmn id}")
    public ResponseEntity<String> columnDelete(@PathVariable Long id) {
        String messge = columnService.columnDelete(id);
        return ResponseEntity.ok(messge);
    }

}

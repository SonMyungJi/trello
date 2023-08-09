package com.example.trello.controller;

import com.example.trello.dto.SectionListResponseDto;
import com.example.trello.dto.SectionRequestDto;
import com.example.trello.dto.SectionResponseDto;
import com.example.trello.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    //section 조회
    @GetMapping("/section")
    public ResponseEntity<SectionListResponseDto> findBySectionWithCard() {
        SectionListResponseDto result = sectionService.findBySectionWithCard();
            return ResponseEntity.ok().body(result);
        }

    //section 생성
    @PostMapping("/section")
    public ResponseEntity<SectionResponseDto> createSection(@RequestBody SectionRequestDto sectionRequestDto) {
        SectionResponseDto createSection = sectionService.createSection(sectionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createSection);
    }

    //section 이름 수정
    @PutMapping("/section/{sectionId}")
    public ResponseEntity<SectionResponseDto> updateSectionName(@PathVariable Long sectionId, @RequestBody SectionRequestDto requestDto) {
        try {
            SectionResponseDto result = sectionService.updateSectionName(sectionId, requestDto);
            return ResponseEntity.ok().body(result);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //section 삭제
    @DeleteMapping("/section/{sectionId}")
    public ResponseEntity<String> sectionDelete(@PathVariable Long sectionId) {
        String message = sectionService.sectionDelete(sectionId);
        return ResponseEntity.ok(message);
    }

}

package com.example.trello.service;

import com.example.trello.dto.CardResponseDto;
import com.example.trello.dto.SectionListResponseDto;
import com.example.trello.dto.SectionRequestDto;
import com.example.trello.dto.SectionResponseDto;
import com.example.trello.entity.Board;
import com.example.trello.entity.Card;
import com.example.trello.entity.Section;
import com.example.trello.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final BoardServiceImpl boardService;

    //컬럼 조회
    public SectionListResponseDto findBySectionWithCard() {
        List<SectionResponseDto> columnList = sectionRepository.findAll().stream()
                .map(SectionResponseDto::new)
                .collect(Collectors.toList());

        return new SectionListResponseDto(columnList);
    }

    //컬럼 생성
    public SectionResponseDto createSection(Long boardId, SectionRequestDto requestDto) {
        Board board = boardService.findBoard(boardId);
        Section section = sectionRepository.save(new Section(board, requestDto));
        return new SectionResponseDto(section);
    }

    //컬럼 이름 수정
    public SectionResponseDto updateSectionName(Long sectionId, SectionRequestDto requestDto) {
        Section section = sectionRepository.findById(sectionId).orElse(null);
        findSection(sectionId);
        if (section != null) {
            section.setSectionName(requestDto);
            sectionRepository.save(section);
            return new SectionResponseDto(section);
        }
        return new SectionResponseDto(section);
    }



    //컬럼 삭제
    public String sectionDelete(Long sectionId) {
        findSection(sectionId);
        sectionRepository.deleteById(sectionId);
        return "삭제가 완료되었습니다.";
    }



    public Section findSection(Long sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() ->
                new IllegalArgumentException("해당 칼럼는 존재하지 않습니다."));
    }
}

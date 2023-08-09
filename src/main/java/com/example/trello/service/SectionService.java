package com.example.trello.service;

import com.example.trello.dto.SectionListResponseDto;
import com.example.trello.dto.SectionRequestDto;
import com.example.trello.dto.SectionResponseDto;
import com.example.trello.entity.Section;
import com.example.trello.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;

    //컬럼 조회
    public SectionListResponseDto findBySectionWithCard() {
        List<SectionResponseDto> columnList = sectionRepository.findAll().stream()
                .map(SectionResponseDto::new)
                .collect(Collectors.toList());

        return new SectionListResponseDto(columnList);
    }

    //컬럼 생성
    public SectionResponseDto createSection(SectionRequestDto requestDto) {
        Section section = new Section(requestDto);
//        columns.setColumnsName(requestDto);
        return new SectionResponseDto(sectionRepository.save(section));
    }

    //컬럼 이름 수정
    public SectionResponseDto updateSectionName(Long sectionId, SectionRequestDto requestDto) {
        Section section = sectionRepository.findById(sectionId).orElse(null);
        if (section != null) {
            section.setSectionName(requestDto);
            sectionRepository.save(section);
            return new SectionResponseDto(section);
        }
        return null;
    }



    //컬럼 삭제
    public String sectionDelete(Long sectionId) {
        sectionRepository.deleteById(sectionId);
        return null;
    }



    public Section findSection(Long sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() ->
                new IllegalArgumentException("해당 칼럼는 존재하지 않습니다."));
    }
}

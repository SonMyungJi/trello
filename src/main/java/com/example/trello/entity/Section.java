package com.example.trello.entity;

import com.example.trello.dto.SectionRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long sectionId;

    @Column(nullable = true)
    private String sectionName;

    @Column(nullable = false, unique = true)
    private Long sectionIndex; // section의 위치를 저장하기 위한 파라미터

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @OneToMany(mappedBy = "section", cascade = CascadeType.REMOVE)
    private Set<Card> cards = new HashSet<>();

    public Section(Board board, SectionRequestDto requestDto) {
        this.board = board;
        this.sectionName = requestDto.getSectionName();
        this.sectionIndex = requestDto.getSectionIndex();
    }

    public void setSectionName(SectionRequestDto sectionRequestDto) {
        this.sectionName = sectionRequestDto.getSectionName();
    }

//    @OneToMany(mappedBy = "cards")
//    private List<cards> cardsList = new ArrayList<>();

//    public List<Cards> getCards() {
//        return cards;
//    }
//
//    public void setCards(List<Cards> cards) {
//        this.cards = cards;
//    }
}
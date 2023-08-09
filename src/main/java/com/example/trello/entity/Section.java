package com.example.trello.entity;

import com.example.trello.dto.SectionRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long sectionId;

    @Column(nullable = false, unique = true)
    private String sectionName;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @OneToMany(mappedBy = "section", cascade = CascadeType.REMOVE)
    private Set<Card> cards = new HashSet<>();

    public Section(Board board, SectionRequestDto requestDto) {
        this.board = board;
        this.sectionName = requestDto.getSectionName();
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

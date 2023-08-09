package com.example.trello.dto;

import com.example.trello.entity.Card;
import com.example.trello.entity.Comment;
import com.example.trello.entity.Section;
import com.example.trello.entity.User;
import com.example.trello.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CardResponseDto {

    private String cardName;
    private String cardDesc;
    private String cardColor;
//    private Long user;
    private Section section;
    private Date dueDate;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public CardResponseDto(Card card) {
        this.cardName = card.getCardName();
        this.cardDesc = card.getCardDesc();
        this.cardColor = card.getCardColor();
        for (Comment comment : card.getComments()) {
            commentList.add(new CommentResponseDto(comment));
        }
//        this.user = card.getWorkerId();
        this.section = card.getSection();
        this.dueDate = card.getDueDate();
        }
    }


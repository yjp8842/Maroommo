package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Answer;
import com.a406.mrm.model.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerInsertDto {

    public AnswerInsertDto(Answer answer) {
        this.content = answer.getContent();
        this.question_id = answer.getQuestion().getId();
        this.user_id = answer.getUser().getId();
    }

    private String content;
    private int question_id;
    private String user_id;
}
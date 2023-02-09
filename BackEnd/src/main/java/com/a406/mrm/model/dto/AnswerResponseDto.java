package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Answer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto {

    public AnswerResponseDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.createtime = answer.getCreateTime();
        this.good = answer.getGood();
        this.question_id = answer.getQuestion().getId();
        this.user_id = answer.getUser().getId();

    }
    private int id;
    private String content;
    private LocalDateTime createtime;
    private int good;
    private int question_id;
    private String user_id;


}
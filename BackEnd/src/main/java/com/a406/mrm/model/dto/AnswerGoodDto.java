package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Answer;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerGoodDto {

    public AnswerGoodDto(Answer answer) {
        this.id = answer.getId();
        this.good = answer.getGood();

    }
    private int id;
    private int good;
}
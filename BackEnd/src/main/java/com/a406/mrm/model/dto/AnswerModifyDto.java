package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Answer;
import com.a406.mrm.model.entity.Comment;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerModifyDto {

    public AnswerModifyDto(Answer answer) {
        this.content = answer.getContent();
    }

    private String content;

}
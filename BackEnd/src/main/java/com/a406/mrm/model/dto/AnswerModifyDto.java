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
        this.id = answer.getId();
        this.content = answer.getContent();
        this.user_id = answer.getUser().getId();
    }

    private int id;
    private String content;
    private String user_id;

}
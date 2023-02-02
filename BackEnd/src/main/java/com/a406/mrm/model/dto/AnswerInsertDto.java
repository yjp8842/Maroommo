package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Answer;
import com.a406.mrm.model.entity.Comment;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnswerInsertDto {

    public AnswerInsertDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.createtime = answer.getCreateTime();
        this.question_id = answer.getQuestion().getId();
        this.user_id = answer.getUser().getId();

    }

    private int id;
    private String content;
    private Date createtime;
    private int question_id;
    private String user_id;


}
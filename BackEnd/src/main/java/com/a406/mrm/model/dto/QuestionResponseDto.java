package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.Question;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class QuestionResponseDto {
    public QuestionResponseDto(Question question){
        this.id = question.getId();
        this.title = question.getTitle();
        this.createTime = question.getCreateTime();
        this.status = question.getStatus();
        this.user = question.getUser().getId();
    }

    private int id;
    private String title;
    private Date createTime;
    private int status;
    private String user;

}

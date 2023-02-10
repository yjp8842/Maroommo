package com.a406.mrm.model.dto;

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
public class QuestionResponseStatusDto {
    public QuestionResponseStatusDto(Question question){
        this.status = question.getStatus();
        this.id = question.getId();
        this.user_id = question.getUser().getId();
    }

    private int status;
    private int id;
    private String user_id;


}

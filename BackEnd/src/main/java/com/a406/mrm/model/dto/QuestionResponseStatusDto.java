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
    }

    private int status;


}

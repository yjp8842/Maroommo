package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Question;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
        this.views = question.getViews();
        this.status = question.getStatus();
        this.user_id = question.getUser().getId();
    }

    private int id;
    private String title;
    private LocalDateTime createTime;
    private int views;
    private int status;
    private String user_id;

}

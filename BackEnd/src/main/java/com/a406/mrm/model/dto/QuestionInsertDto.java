package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.Question;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInsertDto {

    public QuestionInsertDto(Question question) {
        this.title = question.getTitle();
        this.content = question.getContent();
        this.picture = question.getPicture();
        this.categorysub_id = question.getCategorySub().getId();
        this.user_id = question.getUser().getId();
    }

    private String title;
    private String content;
    private String picture;
    private int categorysub_id;
    private String user_id;
}
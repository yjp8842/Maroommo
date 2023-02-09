package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Question;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
public class QuestionResponseAnswerDto {
    public QuestionResponseAnswerDto(Question question){
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.createTime = question.getCreateTime();
        this.views = question.getViews();
        this.status = question.getStatus();
        this.picture = question.getPicture();
        this.user_id = question.getUser().getId();
        this.answers = question.getAnswers().stream().map(x->new AnswerResponseDto(x)).collect(Collectors.toList());
    }

    private int id;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private int views;
    private int status;
    private String picture;
    private String user_id;
    private List<AnswerResponseDto> answers = new ArrayList<>();

}

package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.AnswerInsertDto;
import com.a406.mrm.model.dto.AnswerModifyDto;
import com.a406.mrm.model.dto.CommentInsertDto;
import com.a406.mrm.model.dto.CommentModifyDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answer")
@Entity
public class Answer {
    public Answer(AnswerInsertDto answerInsertDto, Question question, User user) {
        this.content = answerInsertDto.getContent();
        this.createTime = answerInsertDto.getCreatetime();
        this.question = question;
        this.user = user;
    }

    public Answer(AnswerModifyDto answerModifyDto, int answer_id) {
        this.id = answer_id;
        this.content = answerModifyDto.getContent();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private int good;
    private int bad;

    @ManyToOne(targetEntity = Question.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
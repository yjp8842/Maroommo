package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.BoardInsertDto;
import com.a406.mrm.model.dto.BoardModifyDto;
import com.a406.mrm.model.dto.QuestionInsertDto;
import com.a406.mrm.model.dto.QuestionModifyDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
@Entity
public class Question {
    public Question(QuestionInsertDto questionInsertDto, CategorySub categorySub, User user) {
        this.title = questionInsertDto.getTitle();
        this.content = questionInsertDto.getContent();
        this.createTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.views = 0;
        this.status = 0;
        this.picture = questionInsertDto.getPicture();
        this.categorySub = categorySub;
        this.user = user;
    }

    public Question(QuestionModifyDto questionModifyDto, int question_id) {
        this.id = question_id;
        this.title = questionModifyDto.getTitle();
        this.content = questionModifyDto.getContent();
        this.picture = questionModifyDto.getPicture();
        this.status = questionModifyDto.getStatus();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private int views;
    private int status;
    private String picture;

    @ManyToOne(targetEntity = CategorySub.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "categorySub_id")
    private CategorySub categorySub;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();


}
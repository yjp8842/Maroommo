package com.a406.mrm.model.entity;

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

    public Question(String title, String content, String picture, User user, Room room) {
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.room = room;
        this.user = user;
        this.views = 0;
        this.createTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.status = 0;
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

    @ManyToOne(targetEntity = Room.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();


}
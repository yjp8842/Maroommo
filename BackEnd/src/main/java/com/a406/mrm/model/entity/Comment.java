package com.a406.mrm.model.entity;


import com.a406.mrm.model.dto.CommentInsertDto;
import com.a406.mrm.model.dto.CommentModifyDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Entity
public class Comment {
    public Comment(CommentInsertDto commentInsertDto, Board board, User user) {
        this.content = commentInsertDto.getContent();
        this.createTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.board = board;
        this.user = user;
    }

    public Comment(CommentModifyDto commentModifyDto, int comment_id) {
        this.id = comment_id;
        this.content = commentModifyDto.getContent();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private LocalDateTime createTime;

    @ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
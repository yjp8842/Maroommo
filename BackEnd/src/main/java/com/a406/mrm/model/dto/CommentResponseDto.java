package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createtime = comment.getCreateTime();
        this.board_id = comment.getBoard().getId();
        this.user_id = comment.getUser().getId();

    }
    private int id;
    private String content;
    private LocalDateTime createtime;
    private int board_id;
    private String user_id;


}
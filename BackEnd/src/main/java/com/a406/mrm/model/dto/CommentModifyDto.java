package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.Comment;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentModifyDto {

    public CommentModifyDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.user_id = comment.getUser().getId();
    }

    private int id;
    private String content;
    private String user_id;

}
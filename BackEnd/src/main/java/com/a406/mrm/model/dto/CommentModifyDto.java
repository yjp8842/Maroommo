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
        this.content = comment.getContent();
    }

    private String content;

}
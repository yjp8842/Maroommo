package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Board;
import com.a406.mrm.model.entity.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BoardResponseCommentDto {
    public BoardResponseCommentDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createTime = board.getCreateTime();
        this.views = board.getViews();
        this.user_id = board.getUser().getId();
//        this.comments = board.getComments().stream().map(x -> x.getContent()).collect(Collectors.toList());
        this.comments = board.getComments().stream().map(x->new CommentResponseDto(x)).collect(Collectors.toList());
    }

    private int id;
    private String title;
    private String content;
    private Date createTime;
    private int views;
    private String user_id;
//    private List<String> comments = new ArrayList<>();
    private List<CommentResponseDto> comments = new ArrayList<>();
}

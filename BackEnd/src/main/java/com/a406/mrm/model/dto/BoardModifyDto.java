package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Board;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardModifyDto {

    public BoardModifyDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.picture = board.getPicture();
        this.user_id = board.getUser().getId();
    }

    private int id;
    private String title;
    private String content;
    private String picture;
    private String user_id;

}
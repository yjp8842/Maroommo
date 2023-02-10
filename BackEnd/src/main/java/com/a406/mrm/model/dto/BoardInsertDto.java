package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Board;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsertDto {

    public BoardInsertDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.picture = board.getPicture();
        if(board.getCategorySub() != null) {
            this.categorysub_id = board.getCategorySub().getId();
        }
        this.user_id = board.getUser().getId();
    }
    private String title;
    private String content;
    private String picture;
    private int categorysub_id;
    private String user_id;
}
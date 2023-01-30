package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Board;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.content = board.getContent();
        this.user = board.getUser().getId();
    }

    private int id;
    private String content;

    private String user;

}

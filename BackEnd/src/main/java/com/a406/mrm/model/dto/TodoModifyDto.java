package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Todo;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TodoModifyDto {
    public TodoModifyDto(Todo todo){
        this.id = todo.getId();
        this.content = todo.getContent();
        this.tags = todo.getTodoTags().stream().map(x->x.getName()).collect(Collectors.toList());
        this.roomId = todo.getRoom().getId();
    }
    private int id;
    private String content;
    private List<String> tags;
    private int roomId;
}

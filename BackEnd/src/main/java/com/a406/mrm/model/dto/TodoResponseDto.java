package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Todo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
public class TodoResponseDto {
    public TodoResponseDto(Todo todo){
        this.id = todo.getId();
        this.content = todo.getContent();
        this.tags = todo.getTodoTags().stream().map(x->x.getName()).collect(Collectors.toList());
        this.state = todo.getState();
    }
    private int id;
    private List<String> tags = new ArrayList<>();
    private String content;
    private int state;
}

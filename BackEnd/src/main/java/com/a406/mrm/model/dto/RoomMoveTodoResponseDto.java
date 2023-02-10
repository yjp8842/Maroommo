package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Todo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomMoveTodoResponseDto {
    public RoomMoveTodoResponseDto(Todo todo){
        this.id = todo.getId();
        this.content = todo.getContent();
        this.tags = todo.getTodoTags().stream().map(x->x.getName()).collect(Collectors.toList());
        this.state = todo.getState();
        this.todotimes = todo.getTodoTimes().stream().map(x->new RoomMoveTodoTimeResponseDto(x)).collect(Collectors.toList());
    }
    private int id;
    private List<String> tags = new ArrayList<>();
    private String content;
    private int state;
    private List<RoomMoveTodoTimeResponseDto> todotimes = new ArrayList<>();
}

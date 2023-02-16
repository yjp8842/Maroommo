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
public class TodoResponseDto {
    public TodoResponseDto(Todo todo, String startTime){
        this.id = todo.getId();
        this.content = todo.getContent();
        this.tags = todo.getTodoTags().stream().map(x->x.getName()).collect(Collectors.toList());
        this.state = todo.getState();
        this.startTime = startTime;
        this.roomName = todo.getRoom() == null ? "" : todo.getRoom().getName();
        this.todoTimes = todo.getTodoTimes().stream().map(x->new TodoTimeDto(x)).collect(Collectors.toList());
        this.doingTimeId = todo.getDoingTimeId();
    }
    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.tags = todo.getTodoTags().stream().map(x -> x.getName()).collect(Collectors.toList());
        this.state = todo.getState();
        this.roomName = todo.getRoom() == null ? "" : todo.getRoom().getName();
        this.todoTimes = todo.getTodoTimes().stream().map(x->new TodoTimeDto(x)).collect(Collectors.toList());
    }
    private int id;
    private List<String> tags = new ArrayList<>();
    private String content;
    private int state;
    private String startTime;
    private String roomName;
    private int doingTimeId;
    private List<TodoTimeDto> todoTimes = new ArrayList<>();
}

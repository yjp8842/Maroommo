package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Todo;
import com.a406.mrm.model.entity.TodoTag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class TodoSearchDto {
    public TodoSearchDto(Todo todo){
        this.tags = todo.getTodoTags();
        this.id = todo.getId();
        this.content = todo.getContent();
        this.startTime = todo.getStartTime();
        this.state = todo.getState();
    }
    private int id;
    private String content;
    private Date startTime;
    private List<TodoTag> tags = new ArrayList<>();
    private int state;
}

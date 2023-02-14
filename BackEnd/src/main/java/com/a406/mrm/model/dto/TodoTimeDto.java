package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.TodoTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
public class TodoTimeDto {
    private String userId ;
    private int todoId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TodoTimeDto(TodoTime todoTime){
        this.userId = todoTime.getUser().getId();
        this.todoId = todoTime.getTodo().getId();
        this.startTime = LocalDateTime.ofInstant(todoTime.getStartTime().toInstant(), ZoneId.systemDefault());
        this.endTime = todoTime.getEndTime() == null ? LocalDateTime.now() : LocalDateTime.ofInstant(todoTime.getEndTime().toInstant(),ZoneId.systemDefault());
    }
}

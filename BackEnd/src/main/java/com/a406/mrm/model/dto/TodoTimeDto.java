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
    private int totalSec;
    private String content;
    private String userNickname;
    public TodoTimeDto(TodoTime todoTime){
        this.userId = todoTime.getUser().getId();
        this.todoId = todoTime.getTodo().getId();
        this.startTime = LocalDateTime.ofInstant(todoTime.getStartTime().toInstant(), ZoneId.systemDefault());
        this.endTime = todoTime.getEndTime() == null ? null : LocalDateTime.ofInstant(todoTime.getEndTime().toInstant(),ZoneId.systemDefault());
        this.totalSec = 0;
        this.content = todoTime.getTodo().getContent();
        this.userNickname = todoTime.getUser().getNickname();
        if(this.endTime!=null){
            LocalDateTime TIME = LocalDateTime.ofInstant(todoTime.getTotalTime().toInstant(), ZoneId.systemDefault());
            this.totalSec = ((TIME.getHour()-9) * 60 + TIME.getMinute())*60 + TIME.getSecond();
        }
    }
}

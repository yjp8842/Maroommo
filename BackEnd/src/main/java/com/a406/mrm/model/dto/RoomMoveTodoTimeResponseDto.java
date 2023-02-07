package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.TodoTime;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomMoveTodoTimeResponseDto {
    public RoomMoveTodoTimeResponseDto(TodoTime todoTime){
        this.startTime = todoTime.getStartTime();
        this.endTime = todoTime.getEndTime();
        this.totalTime = todoTime.getTotalTime();

    }
    private Date startTime;
    private Date endTime;
    private Date totalTime;

}

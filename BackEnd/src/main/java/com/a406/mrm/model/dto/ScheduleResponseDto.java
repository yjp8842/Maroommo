package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Schedule;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ScheduleResponseDto {
    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.content = schedule.getContent();
        this.startTime = schedule.getStartTime();
        this.userId = schedule.getUser().getId();
        this.roomId = schedule.getRoom().getId();
    }
    private int id;
    private String userId;
    private int roomId;
    private String content;
    private String startTime;
}

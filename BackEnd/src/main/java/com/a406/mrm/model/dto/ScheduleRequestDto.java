package com.a406.mrm.model.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ScheduleRequestDto {
    private String content;
    private Date startTime;
    /**
     * 바뀐 schedule이 startTime이랑 endTime이 존재하나?...
     */
    private Date endTime;
    private String userId;
    private int roomId;

}

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
    private String userId;
    private int roomId;
}

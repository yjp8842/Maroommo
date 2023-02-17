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
    private int year;
    private int month;
    private int day;
    private String userId;
    private int roomId;
}

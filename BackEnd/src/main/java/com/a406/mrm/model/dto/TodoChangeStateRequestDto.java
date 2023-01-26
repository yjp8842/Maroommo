package com.a406.mrm.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class TodoChangeStateRequestDto {
    private int doingId;
    private int doneId;
    private int todoId;
    private int doingTimeId;
}

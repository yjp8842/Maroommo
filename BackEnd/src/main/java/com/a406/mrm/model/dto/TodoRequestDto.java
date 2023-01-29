package com.a406.mrm.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@ToString
public class TodoRequestDto {
    private String content;
    private Date startTime;
    private List<String> tags;
    private int roomId;
}

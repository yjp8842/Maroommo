package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.RoomMemo;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomMoveResponseDto {

    private int id;
    private String name;
    private String profile;
    private String intro;
    private String code;
    private String roomMemo;

    private List<RoomMoveUserResponseDto> users = new ArrayList<>();
    private List<ScheduleResponseDto> schedules = new ArrayList<>();

    public RoomMoveResponseDto(Room room, RoomMemo roomMemo, List<ScheduleResponseDto> schedules){
        this.id = room.getId();
        this.name = room.getName();
        this.profile = room.getProfile();
        this.intro = room.getIntro();
        this.code = room.getCode();

        if(roomMemo != null)
            this.roomMemo = roomMemo.getContent();

        this.users = room.getUsers()
                            .stream()
                            .map(x->new RoomMoveUserResponseDto(x.getUser())).collect(Collectors.toList());

        this.schedules = schedules;
    }
}

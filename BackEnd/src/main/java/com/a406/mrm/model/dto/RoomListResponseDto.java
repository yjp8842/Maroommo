package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.RoomMemo;
import com.a406.mrm.model.entity.UserHasRoom;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomListResponseDto {

    private int id;
    private String name;
    private String profile;
    private String intro;
//    private List<ScheduleResponseDto> schedules = new ArrayList<>();

    public RoomListResponseDto(UserHasRoom userHasRoom){
        this.id = userHasRoom.getRoom().getId();
        this.name = userHasRoom.getRoom().getName();
        this.profile = userHasRoom.getRoom().getProfile();
        this.intro = userHasRoom.getRoom().getIntro();

//        this.schedules = userHasRoom.getRoom()
//                                    .getSchedules()
//                                    .stream()
//                                    .map(x->new ScheduleResponseDto(x)).collect(Collectors.toList());
    }
}
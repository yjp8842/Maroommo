package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.RoomMemo;
import com.a406.mrm.model.entity.UserHasRoom;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RoomResponseDto {

    public RoomResponseDto(UserHasRoom userHasRoom, RoomMemoDto roomMemoDto){
        this.id = userHasRoom.getRoom().getId();
        this.name = userHasRoom.getRoom().getName();
        this.profile = userHasRoom.getRoom().getProfile();
        this.intro = userHasRoom.getRoom().getIntro();
        this.code = userHasRoom.getRoom().getCode();

        this.schedules = userHasRoom.getRoom().getSchedules()
                .stream()
                .map(x->new ScheduleResponseDto(x)).collect(Collectors.toList());

        this.roomMemo = roomMemoDto;
    }

    public RoomResponseDto(Room room, RoomMemo roomMemo){
        this.id = room.getId();
        this.name = room.getName();
        this.profile = room.getProfile();
        this.intro = room.getIntro();
        this.code = room.getCode();

        this.schedules = room.getSchedules()
                            .stream()
                            .map(x->new ScheduleResponseDto(x)).collect(Collectors.toList());

        this.roomMemo = new RoomMemoDto(roomMemo);
    }

    private int id;
    private String name;
    private String profile;
    private String intro;
    private String code;

    private List<ScheduleResponseDto> schedules = new ArrayList<>();

    private RoomMemoDto roomMemo = null;
}

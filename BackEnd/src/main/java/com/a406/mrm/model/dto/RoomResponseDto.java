package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RoomResponseDto {

    public RoomResponseDto(Room room){
        this.id = room.getId();
        this.name = room.getName();
        this.profile = room.getProfile();
        this.intro = room.getIntro();
        this.memo = room.getMemo();
        this.code = room.getCode();
    }
    private int id;
    private String name;
    private String profile;
    private String intro;
    private String memo;
    private String code;
}

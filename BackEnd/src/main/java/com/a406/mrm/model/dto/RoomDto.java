package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RoomDto {

    public RoomDto(Room room){
        this.id = room.getId();
        this.name = room.getName();
        this.profile = room.getProfile();
        this.intro = room.getIntro();
        this.memo = room.getMemo();
    }
    private int id;
    private String name;
    private String profile;
    private String intro;
    private String memo;
}

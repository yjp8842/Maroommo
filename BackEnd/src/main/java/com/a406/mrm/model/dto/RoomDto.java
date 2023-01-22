package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

@Data
@Getter
@Setter
public class RoomDto {

    public RoomDto(Room room){
        this.name = room.getName();
        this.profile = room.getProfile();
        this.intro = room.getIntro();
        this.memo = room.getMemo();
    }
    private String name;
    private String profile;
    private String intro;
    private String memo;
}

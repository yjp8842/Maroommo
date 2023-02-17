package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomResponseDto {
    private String intro ;
    private String name ;
    private String profile;
    public RoomResponseDto(Room room){
        this.intro = room.getIntro();
        this.name = room.getName();
        this.profile = room.getProfile();
    }
}

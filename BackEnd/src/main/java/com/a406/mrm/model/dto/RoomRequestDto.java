package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RoomRequestDto {
    public RoomRequestDto(Room room){
        this.name = room.getName();
        this.intro = room.getIntro();
        this.profile = room.getProfile();
        this.memo = room.getMemo();
    }
    private String name;
    private String profile;
    private String intro;
    private String memo;
}

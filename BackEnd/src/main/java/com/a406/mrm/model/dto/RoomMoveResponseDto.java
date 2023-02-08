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

    private List<RoomMoveCategoryResponseDto> categories = new ArrayList<>();
    private List<RoomMoveUserResponseDto> users = new ArrayList<>();

    public RoomMoveResponseDto(Room room, RoomMemo roomMemo){
        this.id = room.getId();
        this.name = room.getName();
        this.profile = room.getProfile();
        this.intro = room.getIntro();
        this.code = room.getCode();

        if(roomMemo != null)
            this.roomMemo = roomMemo.getContent();

        this.categories = room.getCategories()
                                .stream()
                                .map(x-> new RoomMoveCategoryResponseDto(x)).collect(Collectors.toList());

        this.users = room.getUsers()
                            .stream()
                            .map(x->new RoomMoveUserResponseDto(x.getUser())).collect(Collectors.toList());
    }
}

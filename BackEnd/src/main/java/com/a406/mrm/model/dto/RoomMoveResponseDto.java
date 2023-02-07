package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
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
    public RoomMoveResponseDto(Room room){
        this.id = room.getId();
        this.name = room.getName();
        this.profile = room.getProfile();
        this.intro = room.getIntro();
        this.categories = room.getCategories().stream().map(x-> new RoomMoveCategoryResponseDto(x)).collect(Collectors.toList());
//                Map.of(room.getCategories().stream().map(x -> x.getId()).collect(Collectors.toList()),room.getCategories().stream().map(x -> x.getName()).collect(Collectors.toList()));
        this.users = room.getUsers().stream().map(x->new RoomMoveUserResponseDto(x.getUser())).collect(Collectors.toList());
//        this.users = Map.of(room.getUsers().stream().map(x->x.getUser().getId()).collect(Collectors.toList()),room.getUsers().stream().map(x->x.getUser().getName()).collect(Collectors.toList()));
//        this.todos = room.getTodos().stream().map(x->new RoomMoveTodoResponseDto(x)).collect(Collectors.toList());
        }

    private int id;
    private String name;
    private String profile;
    private String intro;
    private List<RoomMoveCategoryResponseDto> categories = new ArrayList<>();
//    private Map<List<String>, List<String>> users = new HashMap<>();
    private List<RoomMoveUserResponseDto> users = new ArrayList<>();
//    private List<RoomMoveTodoResponseDto> todos = new ArrayList<>();


}

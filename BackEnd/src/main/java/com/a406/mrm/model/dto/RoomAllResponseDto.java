package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RoomAllResponseDto {
    public RoomAllResponseDto(Room room){
        this.id = room.getId();
        this.name = room.getName();
        this.profile = room.getProfile();
        this.intro = room.getIntro();
        this.memo = room.getMemo();
        this.categories = Map.of(room.getCategories().stream().map(x -> x.getId()).collect(Collectors.toList()),room.getCategories().stream().map(x -> x.getName()).collect(Collectors.toList()));
        this.users = Map.of(room.getUsers().stream().map(x->x.getUser().getId()).collect(Collectors.toList()),room.getUsers().stream().map(x->x.getUser().getName()).collect(Collectors.toList()));
        this.todo = Map.of(room.getTodos().stream().map(x->x.getId()).collect(Collectors.toList()),
                Map.of(room.getTodos().stream().map(x->x.getStartTime()).collect(Collectors.toList()),
                        room.getTodos().stream().map(x->x.getEndTime()).collect(Collectors.toList()))
                );
        this.todoTime = Map.of(room.getTodos().stream().map(x->x.getId()).collect(Collectors.toList()) ,
                room.getTodos().stream().map(x->x.getTodoTimes()
                        .stream().map(y->y.getStartTime()+" ----  "+ y.getEndTime()).collect(Collectors.toList()))
                );
//        this.test = null;


    }

    private int id;
    private String name;
    private String profile;
    private String intro;
    private String memo;
    private Map<List<Integer>, List<String>> categories = new HashMap<>();
    private Map<List<String>, List<String>> users = new HashMap<>();
    private Map<List<Integer>,Map<List<Date>,List<Date>>> todo = new HashMap<>();
    private Map<List<Integer>, Stream<List<String>>> todoTime = new HashMap<>();
//    private List<TodoRequestTestDto> test = new ArrayList<>();




}

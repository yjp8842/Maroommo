package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginResponseDto {

    private String id;
    private String email;
    private String name;
    private String nickname;
    private String profile;
    private String intro;
    private String  userMemo;

    private List<RoomListResponseDto> myRooms = new ArrayList<>();
    private List<TodoResponseDto> doing = new ArrayList<>();
    private List<TodoResponseDto> done = new ArrayList<>();
    private List<ScheduleResponseDto> schedules = new ArrayList<>();

    public UserLoginResponseDto(User user, UserMemo userMemo, List<ScheduleResponseDto> schedules, String DATE_FORMAT){
        this.id=user.getId();
        this.email=user.getEmail();
        this.name=user.getName();
        this.nickname=user.getNickname();
        this.profile=user.getProfile();
        this.intro=user.getIntro();

        if(userMemo != null)
            this.userMemo = userMemo.getContent();

        for(Todo todo : user.getTodos()){
            if(todo.getState()==2){
                this.done.add(new TodoResponseDto(todo,new SimpleDateFormat(DATE_FORMAT).format(todo.getStartTime())));
            }else{
                this.doing.add(new TodoResponseDto(todo,new SimpleDateFormat(DATE_FORMAT).format(todo.getStartTime())));
            }
        }

        this.myRooms = user.getRooms()
                            .stream()
                            .map(x->new RoomListResponseDto(x)).collect(Collectors.toList());

        this.schedules = schedules;
    }
}

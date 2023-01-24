package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.Todo;
import com.a406.mrm.model.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class MyRoomFirstDto {
    public MyRoomFirstDto(List<Todo> todos, List<Room> rooms, User user){
        for(Todo todo : todos){
            if(todo.getState()==2){
                this.done.add(todo);
            }else{
                this.doing.add(todo);
            }
        }
        this.nickname = user.getNickname();
        this.intro = user.getIntro();
        this.profile = user.getProfile();
        this.memo = user.getMemo();
        this.myRooms = rooms;
    }

    private String nickname;
    private String intro;
    private String profile;
    private String memo;
    private List<Room> myRooms = new ArrayList<>();
    private List<Todo> doing = new ArrayList<>();
    private List<Todo> done = new ArrayList<>();

}

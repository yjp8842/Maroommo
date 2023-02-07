package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.Todo;
import com.a406.mrm.model.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MyRoomDto {
    public MyRoomDto(List<Todo> todos, User user){
        for(Todo todo : todos){
            if(todo.getState()==2){
                this.done.add(new TodoResponseDto(todo));
            }else{
                this.doing.add(new TodoResponseDto(todo));
            }
        }
        this.nickname = user.getNickname();
        this.intro = user.getIntro();
        this.profile = user.getProfile();
    }


    private String nickname;
    private String intro;
    private String profile;
    private String memo;
    private List<TodoResponseDto> doing = new ArrayList<>();
    private List<TodoResponseDto> done = new ArrayList<>();

}

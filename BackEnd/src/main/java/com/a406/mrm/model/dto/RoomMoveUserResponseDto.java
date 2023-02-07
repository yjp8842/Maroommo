package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Todo;
import com.a406.mrm.model.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomMoveUserResponseDto {
    public RoomMoveUserResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.todos = user.getTodos().stream().map(x-> new RoomMoveTodoResponseDto(x)).collect(Collectors.toList());
//        this.todotimes = todo.getTodoTimes().stream().map(x->new RoomMoveTodoTimeResponseDto(x)).collect(Collectors.toList());
    }
    private String id;
    private String name;
    private List<RoomMoveTodoResponseDto> todos = new ArrayList<>();
}

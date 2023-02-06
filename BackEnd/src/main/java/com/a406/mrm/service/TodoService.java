package com.a406.mrm.service;

import com.a406.mrm.model.dto.TodoChangeStateRequestDto;
import com.a406.mrm.model.dto.TodoModifyDto;
import com.a406.mrm.model.dto.TodoRequestDto;
import com.a406.mrm.model.dto.TodoResponseDto;
import com.a406.mrm.model.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodoAll(String userId);
    Todo addTodo(String userId, TodoRequestDto todoRequestDto);
    int changeState(TodoChangeStateRequestDto todoChangeStateRequestDto);
    Todo modifyTodo(TodoModifyDto todoModifyDto);
    List<TodoResponseDto> searchRoomTodo(int roomId, String userId);
    List<TodoResponseDto> searchMyTodo(String userId);
    List<Todo> getTodoRoomAll(int room_id);
}

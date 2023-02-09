package com.a406.mrm.service;

import com.a406.mrm.model.dto.TodoChangeStateRequestDto;
import com.a406.mrm.model.dto.TodoModifyDto;
import com.a406.mrm.model.dto.TodoRequestDto;
import com.a406.mrm.model.dto.TodoResponseDto;
import com.a406.mrm.model.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodoAll(String userId) throws Exception;
    TodoResponseDto addTodo(String userId, TodoRequestDto todoRequestDto) throws Exception;
    int changeState(TodoChangeStateRequestDto todoChangeStateRequestDto) throws Exception;
    TodoResponseDto modifyTodo(TodoModifyDto todoModifyDto) throws Exception;
    List<TodoResponseDto> searchRoomTodo(int roomId, String userId) throws Exception;
    List<TodoResponseDto> searchMyTodo(String userId) throws Exception;
    List<Todo> getTodoRoomAll(int room_id) throws Exception;
}

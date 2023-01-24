package com.a406.mrm.service;

import com.a406.mrm.model.dto.TodoChangeStateDto;
import com.a406.mrm.model.dto.TodoRequestDto;
import com.a406.mrm.model.dto.TodoResponseDto;
import com.a406.mrm.model.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodoAll(String userId);
    Todo addTodo(String userId, TodoRequestDto todoRequestDto);
    int changeState(TodoChangeStateDto todoChangeStateDto);
    List<TodoResponseDto> searchTodo(int roomId, String userId);
}

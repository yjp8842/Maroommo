package com.a406.mrm.service;

import com.a406.mrm.model.dto.TodoTimeDto;
import com.a406.mrm.model.entity.TodoTime;

import java.util.List;

public interface TodoTimeService {
    List<TodoTime> getTodoTime(int room_id);
    List<TodoTimeDto> getTodayTodoTime(int room_id);
}

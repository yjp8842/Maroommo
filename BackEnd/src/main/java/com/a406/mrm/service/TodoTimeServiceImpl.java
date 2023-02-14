package com.a406.mrm.service;

import com.a406.mrm.model.dto.TodoTimeDto;
import com.a406.mrm.model.entity.*;
import com.a406.mrm.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoTimeServiceImpl implements TodoTimeService {
    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
    private final TodoTimeRepository todoTimeRepository;
    private final RoomRepository roomRepository;
    @Override
    public List<TodoTime> getTodoTime(int room_id) {

        return todoTimeRepository.findById(room_id);
    }

    @Override
    public List<TodoTimeDto> getTodayTodoTime(int room_id) {
        Room room = roomRepository.findById(room_id).orElse(null);
        List<TodoTime> todayTodoTimes = new ArrayList<>();
        room.getUsers().stream().forEach(x->todayTodoTimes.addAll(todoTimeRepository.getTodayTodoTime(x.getUser().getId())));
        return todayTodoTimes.stream().map(x->new TodoTimeDto(x)).collect(Collectors.toList());
    }
}

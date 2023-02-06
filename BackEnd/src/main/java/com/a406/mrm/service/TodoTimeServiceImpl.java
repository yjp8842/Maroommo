package com.a406.mrm.service;

import com.a406.mrm.model.entity.*;
import com.a406.mrm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoTimeServiceImpl implements TodoTimeService {

    private final TodoTimeRepository todoTimeRepository;

    @Override
    public List<TodoTime> getTodoTime(int room_id) {

        return todoTimeRepository.findById(room_id);
    }


}

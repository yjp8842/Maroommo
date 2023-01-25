package com.a406.mrm.service;

import com.a406.mrm.model.dto.TodoChangeStateDto;
import com.a406.mrm.model.dto.TodoRequestDto;
import com.a406.mrm.model.dto.TodoResponseDto;
import com.a406.mrm.model.dto.TodoSearchDto;
import com.a406.mrm.model.entity.Todo;
import com.a406.mrm.model.entity.TodoTag;
import com.a406.mrm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final TodoTagRepository todoTagRepository;
    private final TodoTimeRepository todoTimeRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<Todo> getTodoAll(String userId) {
        return userRepository.findById(userId).get().getTodos();
    }

    @Override
    public Todo addTodo(String userId, TodoRequestDto todoRequestDto) {
        Todo todo = todoRepository.save(new Todo(todoRequestDto,
                userRepository.findById(userId).get(),
                roomRepository.findById(todoRequestDto.getRoomId()).get()));

        List<TodoTag> tags = todoRequestDto.getTags().stream().map(x -> todoTagRepository.save(new TodoTag(x, todo))).collect(Collectors.toList());
        todo.setTodoTags(tags);
        return todo;
    }

    @Override
    public int changeState(TodoChangeStateDto todoChangeStateDto) {
        /***
         * todo -> done
         * todo -> doing
         * todo <-> doing
         * doing -> done
         * done -> todo
         * done -> doing
         */
        int doneId = todoChangeStateDto.getDoneId();
        int doingId = todoChangeStateDto.getDoingId();
        int todoId = todoChangeStateDto.getTodoId();
        if (doneId != -1) {
//            return todoRepository.updateTodoStateDone(doneId);
        }
        return -1;
    }

    @Override
    public List<TodoResponseDto> searchRoomTodo(int roomId, String userId) {
        List<TodoResponseDto> result = todoRepository.findByUserId(userId).stream()
                .filter(x ->
                        (x.getRoom() == null ? false : x.getRoom().getId() == roomId)
                ).map(x -> new TodoResponseDto(x)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<TodoResponseDto> searchMyTodo(String userId) {
        List<TodoResponseDto> result = todoRepository.findByUserId(userId)
                .stream()
                .map(x->new TodoResponseDto(x)).collect(Collectors.toList());
        return result;
    }


}

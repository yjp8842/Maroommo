package com.a406.mrm.service;

import com.a406.mrm.model.dto.TodoChangeStateRequestDto;
import com.a406.mrm.model.dto.TodoRequestDto;
import com.a406.mrm.model.dto.TodoResponseDto;
import com.a406.mrm.model.entity.*;
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
        User user = userRepository.findById(userId).get();
        Room room = todoRequestDto.getRoomId() == -1 ? null : roomRepository.findById(todoRequestDto.getRoomId()).get();
        Todo todo = todoRepository.save(new Todo(todoRequestDto,
                user,
                room));
        user.getTodos().add(todo);
        if(room!=null) {
            room.getTodos().add(todo);
        }
        List<TodoTag> tags = todoRequestDto.getTags().stream().map(x -> todoTagRepository.save(new TodoTag(x, todo))).collect(Collectors.toList());
        todo.setTodoTags(tags);
        return todo;
    }

    @Override
    public int changeState(TodoChangeStateRequestDto todoChangeStateRequestDto) {
        /***
         * todo : 0
         * doing : 1
         * done : 2
         * todo -> done
         * todo -> doing
         * todo <-> doing
         * doing -> done
         * done -> todo
         * done -> doing
         */
        int doneId = todoChangeStateRequestDto.getDoneId();
        int doingId = todoChangeStateRequestDto.getDoingId();
        int todoId = todoChangeStateRequestDto.getTodoId();
        int doingTimeId = todoChangeStateRequestDto.getDoingTimeId();
        int ret = -1;
        if (doingTimeId != -1){
//            todoTimeRepository.updateEndTimeAndTotalTime(doingTimeId);
        }
        if (doneId != -1) {
            Todo done = todoRepository.findById(doneId).get();
            todoRepository.updateEndTimeAndState(doneId);
        }
        if (todoId != -1){
            Todo todo = todoRepository.findById(todoId).get();
            todo.setState(0);
            todoRepository.save(todo);
        }
        if (doingId != -1){
            Todo doing = todoRepository.findById(todoId).get();
            doing.setState(1);
            TodoTime todoTime = new TodoTime(doing.getUser(),doing);
            ret = todoTimeRepository.save(todoTime).getId();
            todoRepository.save(doing);
        }

        return ret;
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

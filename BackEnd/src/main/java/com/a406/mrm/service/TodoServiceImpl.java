package com.a406.mrm.service;

import com.a406.mrm.model.dto.TodoChangeStateDto;
import com.a406.mrm.model.dto.TodoRequestDto;
import com.a406.mrm.model.dto.TodoResponseDto;
import com.a406.mrm.model.dto.TodoSearchDto;
import com.a406.mrm.model.entity.Todo;
import com.a406.mrm.repository.TodoTimeRepository;
import com.a406.mrm.repository.TodoRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final TodoTimeRepository todoTimeRepository;

    @Override
    public List<Todo> getTodoAll(String userId) {
        return userRepository.findById(userId).get().getTodos();
    }

    @Override
    public Todo addTodo(String userId, TodoRequestDto todoRequestDto) {
        Todo todo = new Todo(todoRequestDto, userRepository.findById(userId).get());
        return todoRepository.save(todo);
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
        if(doneId != -1){
//            return todoRepository.updateTodoStateDone(doneId);
        }
        return -1;
    }

    @Override
    public List<TodoResponseDto> searchTodo(int roomId, String userId) {
        List<TodoResponseDto> result = todoRepository.findByUserIdParam(userId).stream()
                .filter(x->(
                        x.getRooms().stream().filter(r->r.getRoom().getId()==roomId)) != null
                ).map(x->new TodoResponseDto(x)).collect(Collectors.toList());
        return result;
    }
}

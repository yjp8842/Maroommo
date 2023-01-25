package com.a406.mrm.controller;

import com.a406.mrm.model.dto.TodoChangeStateDto;
import com.a406.mrm.model.dto.TodoRequestDto;
import com.a406.mrm.model.dto.TodoResponseDto;
import com.a406.mrm.repository.UserRepository;
import com.a406.mrm.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@Api("todo mapping")
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addTodo(@PathVariable("userId") String userId, @RequestBody TodoRequestDto todoRequestDto){
        logger.info("add Todo information : {}", todoRequestDto.toString());
        TodoResponseDto result = new TodoResponseDto(todoService.addTodo(userId,todoRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PatchMapping
    public ResponseEntity<?> changeStateTodo(@RequestBody TodoChangeStateDto todoChangeStateDto){
        todoService.changeState(todoChangeStateDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/{userId}/{roomId}")
    public ResponseEntity<?> searchRoomTodo(@PathVariable(name="roomId") int roomId,
                                        @PathVariable(name="userId") String userId){
        List<TodoResponseDto> result = todoService.searchRoomTodo(roomId,userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> searchMyTodo(@PathVariable(name="userId") String userId){
        List<TodoResponseDto> result = todoService.searchMyTodo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

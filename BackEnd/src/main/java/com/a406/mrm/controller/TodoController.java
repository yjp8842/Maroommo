package com.a406.mrm.controller;

import com.a406.mrm.model.dto.TodoChangeStateRequestDto;
import com.a406.mrm.model.dto.TodoModifyDto;
import com.a406.mrm.model.dto.TodoRequestDto;
import com.a406.mrm.model.dto.TodoResponseDto;
import com.a406.mrm.repository.UserRepository;
import com.a406.mrm.service.TodoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("todo")
@Api("todo mapping")
@RequiredArgsConstructor
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;
    private final UserRepository userRepository;

    /**
     * @param userId
     * @param todoRequestDto
     *              를 통해 todo를 생성한다
     * @return newTodo : 생성한 todof를 반환한다
     */
    @PostMapping("{userId}")
    public ResponseEntity<?> addTodo(@PathVariable("userId") String userId, @RequestBody TodoRequestDto todoRequestDto){
        logger.info("add Todo information : {}", todoRequestDto.toString());

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        TodoResponseDto todoResponseDto = null;

        try {
            todoResponseDto = todoService.addTodo(userId,todoRequestDto);
            resultMap.put("newTodo",todoResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param todoModifyDto
     *                  를 통해 todo를 수정한다
     * @return todo : 수정한 todo를 반환한다
     */
    @PatchMapping
    public ResponseEntity<?> modifyTodo(@RequestBody TodoModifyDto todoModifyDto){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        TodoResponseDto todoResponseDto = null;

        try {
            todoResponseDto = todoService.modifyTodo(todoModifyDto);
            resultMap.put("todo",todoResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param todoChangeStateRequestDto
     *                  를 통해 state를 변경한다
     * @return isChange : 변경 성공 여부를 반환한다
     */
    @PatchMapping("state")
    public ResponseEntity<?> changeStateTodo(@RequestBody TodoChangeStateRequestDto todoChangeStateRequestDto){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            todoService.changeState(todoChangeStateRequestDto);
            resultMap.put("isChange", true);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param roomId
     * @param userId
     *           를 통해 그룹의 todo들을 가져온다
     * @return todos : 그룹의 todo들을 반환한다
     */
    @GetMapping("{roomId}/{userId}")
    public ResponseEntity<?> searchRoomTodo(@PathVariable(name="roomId") int roomId,
                                        @PathVariable(name="userId") String userId){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        List<TodoResponseDto> todoResponseDtoList = null;

        try {
            todoResponseDtoList = todoService.searchRoomTodo(roomId,userId);
            resultMap.put("todos", todoResponseDtoList);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param userId
     *           를 통해 나의 모든 todo들을 가져온다
     * @return myTodos : 나의 모든 todo들을 반환한다
     */
    @GetMapping("{userId}")
    public ResponseEntity<?> searchMyTodo(@PathVariable(name="userId") String userId){


        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        List<TodoResponseDto> todoResponseDtoList = null;

        try {
            todoResponseDtoList = todoService.searchMyTodo(userId);
            resultMap.put("myTodos", todoResponseDtoList);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}

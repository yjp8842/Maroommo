package com.a406.mrm.controller;

import com.a406.mrm.model.dto.MyRoomDto;
import com.a406.mrm.model.dto.MyRoomFirstDto;
import com.a406.mrm.model.dto.RoomResponseDto;
import com.a406.mrm.model.dto.RoomRequestDto;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.model.entity.Todo;
import com.a406.mrm.repository.RoomRepository;
import com.a406.mrm.repository.UserHasRoomRepository;
import com.a406.mrm.repository.UserRepository;
import com.a406.mrm.service.RoomService;
import com.a406.mrm.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/room"})
@Api("room Controller API")
public class RoomController {
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
    @Autowired
    private RoomService roomService;
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserHasRoomRepository userHasRoomRepository;

    @ApiOperation("make a room(=group)")
    @PostMapping("/{userId}")
    public ResponseEntity<?> addRoom(//@RequestHeader(value="Authorization") String token,
                                       @PathVariable("userId") String userId,
                                       @RequestBody
                                           @ApiParam("room register information") RoomRequestDto roomRequestDto) {
        logger.debug("new Room information : {}", roomRequestDto.toString());
        RoomResponseDto addRoomResult = new RoomResponseDto(roomService.makeRoom(roomRequestDto,userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(addRoomResult);
    }
    @ApiOperation("Delete room")
    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> removeGroup(@PathVariable("roomId") int roomId){
        roomService.removeRoom(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ApiOperation("modify room name")
    @PatchMapping("/name/{roomId}/{name}")
    public ResponseEntity<?> modifyName(@PathVariable("roomId") int roomId,
                                        @PathVariable("name") String name){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.modifyName(roomId,name));
    }

    @ApiOperation("modify room intro")
    @PatchMapping("/intro/{roomId}")
    public ResponseEntity<?> modifyIntro(@PathVariable("roomId") int roomId, @RequestBody String intro){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.modifyIntro(roomId,intro));
    }

    @ApiOperation("modfy room profile")
    @PatchMapping("/profile/{roomId}")
    public ResponseEntity<?> modifyProfile(@PathVariable("roomId") int roomId, @RequestBody String profile){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.modifyProfile(roomId,profile));
    }

    @ApiOperation("first login - my room")
    @GetMapping("/my/first/{userId}")
    public ResponseEntity<?> getMyRoom(@PathVariable("userId") String userId){
        logger.debug("User id : {}", userId);
        List<Todo> todos = todoService.getTodoAll(userId);
        logger.info("todos size : {}", todos.size());
        //service로 빼는게 좋을거 같은데..
        List<Room> rooms = userHasRoomRepository.findByUserId(userId)
                        .stream().map(r -> r.getRoom()).collect(Collectors.toList());
        logger.debug("Todos count : {}", todos.size());
        logger.debug("Rooms count : {}", rooms.size());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MyRoomFirstDto(todos,rooms,userRepository.findById(userId).get()));
    }
    @ApiOperation("my room - without room information")
    @GetMapping("/my/{userId}")
    public ResponseEntity<?> loginMyRoom(@PathVariable("userId") String userId){
        logger.debug("login User Id : {}", userId);
        List<Todo> todos = todoService.getTodoAll(userId);
        logger.debug("Todos count : {}", todos.size());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MyRoomDto(todos,userRepository.findById(userId).get()));

    }

    @ApiOperation("modify room Memo")
    @PatchMapping("/memo/{roomId}")
    public ResponseEntity<?> modifyMemo(@PathVariable("roomId") int roomId,
                                        @RequestBody @ApiParam("memo modify result") String memo){
        Map<String, String> result = new HashMap<>();
        result.put("result", roomService.modifyMemo(roomId,memo));
        return ResponseEntity.ok().body(result);
    }

}

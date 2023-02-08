package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.repository.RoomRepository;
import com.a406.mrm.repository.TodoTimeRepository;
import com.a406.mrm.repository.UserHasRoomRepository;
import com.a406.mrm.repository.UserRepository;
import com.a406.mrm.service.MemoService;
import com.a406.mrm.service.RoomService;
import com.a406.mrm.service.TodoService;
import com.a406.mrm.service.UserService;
import com.a406.mrm.service.TodoTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private UserService userService;
    @Autowired
    private MemoService memoService;
    @Autowired
    private TodoTimeRepository todoTimeRepository;
    @Autowired
    private TodoTimeService todoTimeService;

    @ApiOperation("make a room(=group)")
    @PostMapping(value = "{userId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addRoom(//@RequestHeader(value="Authorization") String token,
                                       @PathVariable("userId") String userId,
//                                       @RequestBody @ApiParam("room register information") RoomRequestDto roomRequestDto,
                                     @RequestParam String name,
                                     @RequestParam String intro,
                                     @RequestParam MultipartFile profile
                                     ) {
//        logger.debug("new Room information : {}", roomRequestDto.toString());

        RoomRequestDto roomRequestDto = new RoomRequestDto(intro,name);
        RoomResponseDto roomResponseDto = roomService.makeRoom(roomRequestDto,userId,profile);

        return ResponseEntity.status(HttpStatus.CREATED).body(roomResponseDto);
    }
    @ApiOperation("enter the room(=group)")
    @PostMapping("enter/{roomId}/{userId}")
    public ResponseEntity<?> enterRoom(@PathVariable("userId") String userId,
                                        @RequestParam @ApiParam("room entry code") String roomCode,
                                        @PathVariable @ApiParam("room id") int roomId) {
        // front에서 전해준 entry code는 (code+id) 처리가 되어 있다
        logger.debug("Room Entry Code information : {}", roomCode);

        // 반환 내용을 어떻게 할지 고민....
        // 해당 코드를 가진 room이 없다면 retrun
        if(!roomService.existsRoomByIdAndCode(roomId, roomCode))
            return ResponseEntity.status(HttpStatus.OK).body(false);

        // 이미 방에 입장되어 있다면 return
        if(roomService.existsUserHasRoomByRoomIdAndUserId(roomId, userId))
            return ResponseEntity.status(HttpStatus.OK).body(false);

        // 있다면 room에 입장 처리 후 room 정보 반환
        RoomResponseDto enterRoomResult = roomService.enterRoom(roomId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(enterRoomResult);
    }
    @ApiOperation("refresh entry code of room(=group)")
    @PatchMapping("/{roomId}/code")
    public ResponseEntity<?> refreshCode(@PathVariable("roomId") int roomId) {
        logger.debug("Room Id information : {}", roomId);
        String afterEntryCode = roomService.updateCode(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(afterEntryCode);
    }
    @ApiOperation("Delete room")
    @DeleteMapping("{roomId}")
    public ResponseEntity<?> removeGroup(@PathVariable("roomId") int roomId){
        roomService.removeRoom(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // /room/{roomId}/name
    @ApiOperation("modify room name")
    @PatchMapping("{roomId}/name")
    public ResponseEntity<?> modifyName(@PathVariable("roomId") int roomId,
                                        @RequestBody String name){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.modifyName(roomId,name));
    }

    @ApiOperation("modify room intro")
    @PatchMapping("{roomId}/intro")
    public ResponseEntity<?> modifyIntro(@PathVariable("roomId") int roomId,
                                         @RequestBody String intro){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.modifyIntro(roomId,intro));
    }

    @ApiOperation("modfy room profile")
    @PatchMapping("/profile/{roomId}")
    public ResponseEntity<?> modifyProfile(@PathVariable("roomId") int roomId, @RequestParam MultipartFile profile){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.modifyProfile(roomId,profile));
    }

    @ApiOperation("my room - without room information")
    @GetMapping("/my/{userId}")
    public ResponseEntity<?> getMyRoom(@PathVariable("userId") String userId){
        logger.debug("[getMyRoom] User Id : {}", userId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        UserLoginResponseDto user = null;
        UserMemoDto userMemo = null;

        try {
            user = userService.getLoginUser(userId);
            userMemo = memoService.findUserMemoByUserId(userId);
            resultMap.put("user",user);
            resultMap.put("userMemo",userMemo);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation("get all room")
    @GetMapping
    public ResponseEntity<?> RoomListAll(){
        List<RoomMoveResponseDto> result = roomService.RoomListAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation("select room and get room info")
    @GetMapping("/{room_id}")
    public ResponseEntity<?> SearchMyRoom(@PathVariable("room_id") int roomId){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        RoomMemoDto roomMemo = null;
        RoomMoveResponseDto move = null;

        try {
            move = new RoomMoveResponseDto(roomRepository.findById(roomId).get());
            roomMemo = memoService.findRoomMemoByRoomId(roomId);
            resultMap.put("move",move);
            resultMap.put("roomMemo", roomMemo);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}

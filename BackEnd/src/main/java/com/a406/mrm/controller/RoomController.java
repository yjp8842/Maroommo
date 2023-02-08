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


    /**
     *  방 만들기 메서드
     *  방 생성 후 해당 방의 정보와 방목록 정보를 반환한다
     */
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

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        RoomMoveResponseDto moveRoomInfo = null ;
        MyRoomResponseDto myRoomInfo = null;

        RoomRequestDto roomRequestDto = new RoomRequestDto(intro,name);

        try {
            moveRoomInfo = roomService.makeRoom(roomRequestDto,userId,profile);
            myRoomInfo = roomService.getMyRoomDto(userId);
            resultMap.put("moveRoomInfo",moveRoomInfo);
            resultMap.put("myRoomInfo",myRoomInfo);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    /**
     *  방 입장 메서드
     *  방 입장 후 해당 방의 정보와 방목록 정보를 반환한다
     */
    @ApiOperation("enter the room(=group)")
    @PostMapping("enter/{roomId}/{userId}")
    public ResponseEntity<?> enterRoom(@PathVariable("userId") String userId,
                                        @RequestParam @ApiParam("room entry code") String roomCode,
                                        @PathVariable @ApiParam("room id") int roomId) {
        // front에서 전해준 entry code는 (code+id) 처리가 되어 있다
        logger.debug("Room Entry Code information : {}", roomCode);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        RoomMoveResponseDto moveRoomInfo = null ;
        MyRoomResponseDto myRoomInfo = null;

        try {
            // 반환 내용을 어떻게 할지 고민....
            // 해당 코드를 가진 room이 없다면 retrun
            if(!roomService.existsRoomByIdAndCode(roomId, roomCode)){
                resultMap.put("fail", "입장 가능한 room이 없습니다");
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }

            // 이미 방에 입장되어 있다면 return
            if(roomService.existsUserHasRoomByRoomIdAndUserId(roomId, userId)){
                resultMap.put("fail", "이미 room에 입장하였습니다");
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }

            moveRoomInfo = roomService.enterRoom(roomId, userId);
            myRoomInfo = roomService.getMyRoomDto(userId);
            resultMap.put("moveRoomInfo",moveRoomInfo);
            resultMap.put("myRoomInfo",myRoomInfo);
            status = HttpStatus.OK;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
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

    // 추후 userId는 security에서 가져오도록 한다
    @ApiOperation("my room - without room information")
    @GetMapping("/my/{userId}")
    public ResponseEntity<?> getMyRoom(@PathVariable("userId") String userId){
        logger.debug("[getMyRoom] User Id : {}", userId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        MyRoomResponseDto myRoomInfo = null;

        try {
            myRoomInfo = roomService.getMyRoomDto(userId);
            resultMap.put("myRoomInfo",myRoomInfo);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation("select room and get room info")
    @GetMapping("/{roomId}")
    public ResponseEntity<?> SearchMyRoom(@PathVariable("roomId") int roomId){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        RoomMoveResponseDto moveRoomInfo = null;

        try {
            moveRoomInfo = roomService.getMoveRoomDto(roomId);
            resultMap.put("moveRoomInfo",moveRoomInfo);
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

}

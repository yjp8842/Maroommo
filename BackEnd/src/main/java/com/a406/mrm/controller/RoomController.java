package com.a406.mrm.controller;

import com.a406.mrm.model.dto.RoomDto;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.service.RoomService;
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
import java.util.Map;

@RestController
@RequestMapping({"/room"})
@Api("room Controller API")
public class RoomController {
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
    @Autowired
    private RoomService roomService;
    @ApiOperation("make a room(=group)")
    @PostMapping("/{userId}")
    public ResponseEntity<?> addRoom(//@RequestHeader(value="Authorization") String token,
                                       @PathVariable("userId") String userId,
                                       @RequestBody
                                           @ApiParam("room register information") RoomDto roomDto) {
        logger.debug("new Room information : {}", roomDto.toString());
        RoomDto addRoomResult = new RoomDto(roomService.makeRoom(roomDto,userId));
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

    @ApiOperation("modify room Memo")
    @PatchMapping("/memo/{roomId}")
    public ResponseEntity<?> modifyMemo(@PathVariable("roomId") int roomId,
                                     @RequestBody @ApiParam("memo modify result") String memo){
        Map<String, String> result = new HashMap<>();
        result.put("result", roomService.modifyMemo(roomId,memo));
        return ResponseEntity.ok().body(result);
    }
}

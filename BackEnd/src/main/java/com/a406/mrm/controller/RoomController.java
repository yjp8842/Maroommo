package com.a406.mrm.controller;

import com.a406.mrm.model.dto.RoomDto;
import com.a406.mrm.model.dto.RoomRegisterFormDto;
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
    public ResponseEntity<?> makeGroup(//@RequestHeader(value="Authorization") String token,
                                       @PathVariable("userId") String userId,
                                       @RequestBody
                                           @ApiParam("room register information") Room room) {
        logger.debug("new Room information : {}", room.toString());
        Room addRoomResult = roomService.makeRoom(room,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RoomDto(addRoomResult));
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

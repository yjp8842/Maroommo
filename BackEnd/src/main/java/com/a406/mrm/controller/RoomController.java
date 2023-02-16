package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.RoomService;
import com.a406.mrm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("room")
@Api("room Controller API")
@RequiredArgsConstructor
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final RoomService roomService;
    private final UserService userService;

    /**
     * @param userId
     * @param name
     * @param intro
     * @param profile
     *              를 통해 방을 생성한다
     * @return moveRoomInfo: 생성한 방의 자세한 정보를 반환한다
     * @return myRoomInfo: 방 목록과 스케쥴 정보를 반환한다
     */
    @ApiOperation("make a room(=group)")
    @PostMapping()
    public ResponseEntity<?> addRoom(@AuthenticationPrincipal User user,
                                     @RequestBody RoomRequestDto roomRequestDto
                                     ) {
//        logger.debug("new Room information : {}", roomRequestDto.toString());

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        RoomMoveResponseDto moveRoomInfo = null ;
        MyRoomResponseDto myRoomInfo = null;

        try {
            moveRoomInfo = roomService.makeRoom(roomRequestDto,user.getUsername());
            myRoomInfo = roomService.getMyRoomDto(user.getUsername());
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
     * @param userId
     * @param roomCode
     * @param roomId
     *             를 통해 방에 입장한다
     * @return fail : 이미 방에 입장하였거나 입장할 방이 없다면 fail 메시지를 반환한다
     * @return moveRoomInfo: 생성한 방의 자세한 정보를 반환한다
     * @return myRoomInfo: 방 목록과 스케쥴 정보를 반환한다
     */
    @ApiOperation("enter the room(=group)")
    @GetMapping("enter/{roomId}")
    public ResponseEntity<?> enterRoom(@AuthenticationPrincipal User user,
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
            if(roomService.existsUserHasRoomByRoomIdAndUserId(roomId, user.getUsername())){
                resultMap.put("fail", "이미 room에 입장하였습니다");
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }

            moveRoomInfo = roomService.enterRoom(roomId, user.getUsername());
            myRoomInfo = roomService.getMyRoomDto(user.getUsername());
            resultMap.put("moveRoomInfo",moveRoomInfo);
            resultMap.put("myRoomInfo",myRoomInfo);
            status = HttpStatus.OK;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param roomId
     *          를 통해 방의 입장 코드를 갱신한다
     * @return code : 갱신한 입장 코드를 반환한다
     */
    @ApiOperation("refresh entry code of room(=group)")
    @PatchMapping("/{roomId}/code")
    public ResponseEntity<?> refreshCode(@PathVariable("roomId") int roomId) {
        logger.debug("Room Id information : {}", roomId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        String afterEntryCode = null;

        try {
            afterEntryCode = roomService.updateCode(roomId);
            resultMap.put("code", afterEntryCode);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param roomId
     *            를 통해 방을 삭제한다
     * @return isDelete : 삭제 성공 여부를 반환한다
     */
    @ApiOperation("Delete room")
    @DeleteMapping("{roomId}")
    public ResponseEntity<?> removeGroup(@PathVariable("roomId") int roomId){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            roomService.removeRoom(roomId);
            resultMap.put("isDelete", true);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param roomId
     * @param name
     * @Param intro
     * @param profile
     *          를 통해 방 정보를 수정한다
     * @return roomName : 수정한 방 정보 반환한다
     */
    @ApiOperation("modify room info")
    @PostMapping("/modify")
    public ResponseEntity<?> modifyRoomInfo(@RequestParam int roomId,
                                        @RequestParam String name,
                                        @RequestParam String intro,
                                        @RequestPart(value="profile",required = false) MultipartFile profile){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        RoomDto roomDto = new RoomDto(intro, name, profile);
        RoomResponseDto roomResponseDto = null;
        try {
            roomResponseDto = roomService.modifyInfo(roomId,roomDto);
            resultMap.put("room", roomResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 추후 userId는 security에서 가져오도록 한다
    /**
     * @param userId
     *            를 통해 마이페이지의 정보를 가져온다
     * @return myRoomInfo : 마이페이지의 정보를 반환한다
     */
    @ApiOperation("my room - without room information")
    @GetMapping("/my")
    public ResponseEntity<?> getMyRoom(@AuthenticationPrincipal User user){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        MyRoomResponseDto myRoomInfo = null;
        UserLoginResponseDto userLoginResponseDto = null;

        try {
            myRoomInfo = roomService.getMyRoomDto(user.getUsername());
            userLoginResponseDto = userService.getLoginUser(user.getUsername());
            resultMap.put("myRoomInfo",myRoomInfo);
            resultMap.put("user",userLoginResponseDto);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param roomId
     * @param userId
     *            를 통해 해당 방의 정보를 가져온다
     * @return moveRoomInfo : 해당 방의 정보를 반환한다
     */
    @ApiOperation("select room and get room info")
    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomDetail(@PathVariable("roomId") int roomId,@AuthenticationPrincipal User user){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        RoomMoveResponseDto moveRoomInfo = null;
        UserLoginResponseDto userLoginResponseDto = null;

        try {
            moveRoomInfo = roomService.getMoveRoomDto(roomId, user.getUsername());
            userLoginResponseDto = userService.getLoginUser(user.getUsername());
            resultMap.put("user",userLoginResponseDto);
            resultMap.put("moveRoomInfo",moveRoomInfo);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param userId
     * @param intro
     * @param nickname
     * @param name
     * @param profile
     *              를 통해 유저의 정보를 변경합니다
     * @return user : 변경항 유저의 정보를 반환합니다
     */
    @ApiOperation("Modify user infomation")
    @PostMapping("user")
    private ResponseEntity<Map<String, Object>> modifyUserInfo(
            @AuthenticationPrincipal User user,
            @RequestParam String intro,
            @RequestParam String nickname,
            @RequestParam String name,
            @RequestPart(value="profile", required = false) MultipartFile profile
    ) {
        UserModifyRequestDto userModifyRequestDto = new UserModifyRequestDto(user.getUsername(), intro,  nickname, name);
        logger.info("[modifyUserInfo] user:{}", user);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        UserModifyResponseDto userModifyResponseDto = null;

        try {
            userModifyResponseDto = userService.modify(userModifyRequestDto,profile);
            resultMap.put("user", userModifyResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}

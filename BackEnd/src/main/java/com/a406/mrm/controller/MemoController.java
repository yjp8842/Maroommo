package com.a406.mrm.controller;

import com.a406.mrm.model.dto.RoomMemoDto;
import com.a406.mrm.model.dto.UserMemoDto;
import com.a406.mrm.repository.RoomMemoRepository;
import com.a406.mrm.service.MemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("memo")
@Api("memo Controller API")
@RequiredArgsConstructor
public class MemoController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final MemoService memoService;
    private final RoomMemoRepository roomMemoRepository;

    /**
     * @param userId
     *            를 통해 회원의 메모를 가져온다
     * @return userMemo : 회원의 메모를 반환한다
     */
    @ApiOperation("get user memo info")
    @GetMapping("user")
    private ResponseEntity<?> getUserMemo(@AuthenticationPrincipal User user){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            UserMemoDto userMemoDto = memoService.findUserMemoByUserId(user.getPassword());
            resultMap.put("userMemo",userMemoDto.getContent());
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param roomId
     *            를 통해 방의 메모를 가져온다
     * @return roomMemo : 방의 메모를 반환한다
     */
    @ApiOperation("get room memo info")
    @GetMapping("room/{roomId}")
    private ResponseEntity<?> getRoomMemo(@PathVariable @ApiParam("room Id") int roomId){
        logger.info("[getRoomMemo] userId:{}", roomId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            RoomMemoDto roomMemoDto = memoService.findRoomMemoByRoomId(roomId);
            resultMap.put("roomMemo",roomMemoDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}

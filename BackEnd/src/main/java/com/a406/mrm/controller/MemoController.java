package com.a406.mrm.controller;

import com.a406.mrm.model.dto.MemoDto;
import com.a406.mrm.model.dto.UserLoginResponseDto;
import com.a406.mrm.service.MemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     *  회원 메모 가져오기 메서드
     */
    @ApiOperation("get user memo info")
    @GetMapping("user/{userId}")
    private ResponseEntity<?> getUserMemo(@PathVariable @ApiParam("userId") String userId
                                      //            ,@AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        logger.info("[getUserMemo] userId:{}", userId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            MemoDto memoDto = memoService.findMemoByUserId(userId);
            resultMap.put("userMemo",memoDto);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  회원 메모 가져오기 메서드
     */
    @ApiOperation("get room memo info")
    @GetMapping("room/{roomId}")
    private ResponseEntity<?> getRoomMemo(@PathVariable @ApiParam("room Id") int roomId){
        logger.info("[getRoomMemo] userId:{}", roomId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            MemoDto memoDto = memoService.findMemoByRoomId(roomId);
            resultMap.put("roomMemo",memoDto);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    /** 테스트용
     *  모든 메모 가져오기 메서드
     */
    @ApiOperation("get all memo info")
    @GetMapping
    private ResponseEntity<?> getMemo(){
        logger.info("[getMemo]");

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            List<MemoDto> memoList = memoService.findAll();
            resultMap.put("memoList",memoList);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}

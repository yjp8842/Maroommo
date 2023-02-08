package com.a406.mrm.controller;

import com.a406.mrm.model.dto.ChatMessageRequestDto;
import com.a406.mrm.model.dto.ChatMessageResponseDto;
import com.a406.mrm.model.dto.RoomMemoDto;
import com.a406.mrm.model.dto.UserMemoDto;
import com.a406.mrm.service.ChatMessageService;

import com.a406.mrm.service.MemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api("Chat Message Controller API")
@RequiredArgsConstructor
public class StompController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatMessageService chatMessageService;
    private final MemoService memoService;

    /**
     *  채팅 보내기 메서드
     *  /app/chat/message로 요청이 오면
     *  해당 메시지를 db에 저장하고, 메시지를 전송한다
     */
    @ApiOperation("Send Chatting Message")
    @MessageMapping("/chat/message")
    public ResponseEntity<Map<String, Object>> sendMessage(
            @ApiParam("Send Message") ChatMessageRequestDto message) {
        logger.info("[sendMessage] 메시지 발송 : "+message);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        ChatMessageResponseDto resMessage = null;
        try {
            // DB에 메시지 저장
            resMessage = chatMessageService.insertChat(message);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        // 메시지를 보낸다
        sendingOperations.convertAndSend("/sub/chat/room/"+message.getRoomId(),resMessage);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    /**
     *  메모 보내기 메서드
     *  /app/chat/memo로 요청이 오면
     *  해당 memo를 db에 저장하고, memo를 전송한다
     *
     *  회원 메모라면 userId가 존재하고
     *  그룹 메모라면 userId = ""이라고 가정한다
     */
    @ApiOperation("Send Room Memo")
    @MessageMapping("/room/memo")
    public ResponseEntity<Map<String, Object>> sendMemo(
            @ApiParam("Send Room Memo") RoomMemoDto memo) {
        logger.info("[sendMemo] 메모 발송 : "+memo);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            // DB에 메모 저장
            memoService.insertMemo(memo);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        // 메시지를 보낸다
        sendingOperations.convertAndSend("/sub/memo/room/"+memo.getRoomId(),memo);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    /////////////////////////////////////////////////////////////
    ////////////////////// 테스트 용 코드
    /////////////////////////////////////////////////////////////

    // swagger test용 send
    @ApiOperation("Tes Send Chatting Message")
    @PostMapping("/chat/message")
    public ResponseEntity<Map<String, Object>> sendTestMessage(
            @RequestBody @ApiParam("Send Message") ChatMessageRequestDto message) {
        logger.info("[sendTestMessage] 메시지 발송 : "+message);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            // DB에 메시지 저장
            chatMessageService.insertChat(message);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation("Test Send Room Memo")
    @PostMapping("/room/memo")
    public ResponseEntity<Map<String, Object>> sendTestRoomMemo(
            @RequestBody @ApiParam("Send Room Message") RoomMemoDto memo) {
        logger.info("[sendTestRoomMemo] 메시지 발송 : "+memo);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            // DB에 메시지 저장
            memoService.insertMemo(memo);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation("Test Send My Memo")
    @PostMapping("/my/memo")
    public ResponseEntity<Map<String, Object>> sendTestMyMemo(
            @RequestBody @ApiParam("Send User Message") UserMemoDto memo) {
        logger.info("[sendTestMyMemo] 메시지 발송 : "+memo);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            // DB에 메시지 저장
            memoService.insertMemo(memo);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}

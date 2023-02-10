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
     * @param message
     *            를 통해 (/app/chat/message)로 요청이 오면 메시지를 저장하고 발송한다
     * @return 저장한 메시지를 반환한다
     */
    @ApiOperation("Send Chatting Message")
    @MessageMapping("/chat/message")
    public ResponseEntity<Map<String, Object>> sendMessage(
            @ApiParam("Send Message") ChatMessageRequestDto message) {
        logger.info("[sendMessage] 메시지 발송 : "+message);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        ChatMessageResponseDto resMessage = null;
        try {
            // DB에 메시지 저장
            resMessage = chatMessageService.insertChat(message);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        // 메시지를 보낸다
        sendingOperations.convertAndSend("/sub/chat/room/"+message.getRoomId(),resMessage);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param memo
     *          를 통해 (/app/chat/memo)로 요청이 오면 메모를 저장하고 전송한다
     * @return
     */
    @ApiOperation("Send Room Memo")
    @MessageMapping("/room/memo")
    public ResponseEntity<Map<String, Object>> sendMemo(
            @ApiParam("Send Room Memo") RoomMemoDto memo) {
        // 프론트에서 보내줄때 메모 DTO에서 id를 제외한 모든 정보가 담겨야 한다
        logger.info("[sendMemo] 메모 발송 : "+memo);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            // DB에 메모 저장
            memoService.insertMemo(memo);
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

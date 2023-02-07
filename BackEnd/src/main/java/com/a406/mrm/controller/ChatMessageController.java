package com.a406.mrm.controller;

import com.a406.mrm.model.dto.ChatMessageRequestDto;
import com.a406.mrm.service.ChatMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api("Chat Message Controller API")
@RequiredArgsConstructor
public class ChatMessageController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatMessageService chatMessageService;

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
        try {
            // DB에 메시지 저장
            chatMessageService.insertChat(message);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        // 메시지를 보낸다
        sendingOperations.convertAndSend("/sub/chat/room/"+message.getRoomId(),message);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

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
}

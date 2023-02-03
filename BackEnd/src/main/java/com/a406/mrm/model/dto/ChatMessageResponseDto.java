package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.ChatMessage;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponseDto {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private int roomId;
    private String userId;
    private String userNickname;
    private String message;
    private LocalDateTime time;

    public ChatMessageResponseDto(ChatMessage chatMessage){
        this.type = MessageType.TALK;
        this.roomId = chatMessage.getRoomId();
        this.userId = chatMessage.getUserId();
        this.userNickname = chatMessage.getUserNickName();
        this.message = chatMessage.getMessage();
        this.time = chatMessage.getTime();
    }
}
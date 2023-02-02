package com.a406.mrm.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequestDto {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private int roomId;
    private String userId;
    private String userNickname;
    private String message;
    private LocalDateTime time;
}
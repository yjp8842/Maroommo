package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.ChatMessageRequestDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "chatMessages")
public class ChatMessage {
    @Id
    private String id;
    private int roomId;
    private String sender;
    private String message;
    private LocalDateTime time; // 보낸 시간

    public ChatMessage(ChatMessageRequestDto chatMessageDto){
        this.roomId=chatMessageDto.getRoomId();
        this.sender=chatMessageDto.getSender();
        this.message=chatMessageDto.getMessage();
        this.time=LocalDateTime.now();
    }
}

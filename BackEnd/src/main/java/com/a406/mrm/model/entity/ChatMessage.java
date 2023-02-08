package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.ChatMessageRequestDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
    private String userId;
    private String userNickName;
    private String message;
    private LocalDateTime time; // 보낸 시간

    public ChatMessage(ChatMessageRequestDto chatMessageDto){
        this.roomId=Integer.parseInt(chatMessageDto.getRoomId());
        this.userId=chatMessageDto.getUserId();
        this.userNickName=chatMessageDto.getUserNickname();
        this.message=chatMessageDto.getMessage();
        this.time=LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}

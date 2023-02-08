package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.ChatMessage;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponseDto {

    private int roomId;
    private String userId;
    private String userNickname;
    private String message;
    private LocalDateTime localDateTime;
    private String date;
    private String time;

    public ChatMessageResponseDto(ChatMessage chatMessage){
        this.roomId = chatMessage.getRoomId();
        this.userId = chatMessage.getUserId();
        this.userNickname = chatMessage.getUserNickName();
        this.message = chatMessage.getMessage();
        this.localDateTime = chatMessage.getTime();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("a HH시 mm분");
        this.date = this.localDateTime.format(dateFormat);
        this.time = this.localDateTime.format(timeFormat);
    }
}
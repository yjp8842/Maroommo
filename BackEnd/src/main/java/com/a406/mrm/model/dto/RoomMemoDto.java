package com.a406.mrm.model.dto;


import com.a406.mrm.model.entity.RoomMemo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomMemoDto {
    private String id;
    private int roomId;
    private String content;

    public RoomMemoDto(RoomMemo roomMemo){
        this.id = roomMemo.getId();
        this.roomId = roomMemo.getRoomId();
        this.content = roomMemo.getContent();
    }

    public RoomMemoDto(int roomId, String content){
        this.roomId = roomId;
        this.content = content;
    }
}

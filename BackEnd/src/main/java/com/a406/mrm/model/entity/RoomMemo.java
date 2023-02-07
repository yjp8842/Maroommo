package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.RoomMemoDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roomMemos")
public class RoomMemo {
    @Id
    private String id;
    private int roomId;
    private String content;

    public RoomMemo(RoomMemoDto memoRoomDto){
        this.roomId = memoRoomDto.getRoomId();
        this.content = memoRoomDto.getContent();
    }
}

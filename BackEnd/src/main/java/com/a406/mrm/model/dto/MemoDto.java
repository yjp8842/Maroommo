package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.Memo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemoDto {
    private String id;
    private int roomId;
    private String userId;
    private String content;

    public MemoDto(Memo memo){
        this.id = memo.getId();
        this.roomId = memo.getRoomId();
        this.userId = memo.getUserId();
        this.content = memo.getContent();
    }
}

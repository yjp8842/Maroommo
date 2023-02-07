package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.MemoDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "memos")
public class Memo {
    @Id
    private String id;
    private int roomId;
    private String userId;
    private String content;

    public Memo(MemoDto memoRequestDto){
        this.roomId = memoRequestDto.getRoomId();
        this.userId = memoRequestDto.getUserId();
        this.content = memoRequestDto.getContent();
    }
}

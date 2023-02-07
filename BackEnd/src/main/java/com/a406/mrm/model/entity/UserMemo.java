package com.a406.mrm.model.entity;

import com.a406.mrm.model.dto.UserMemoDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userMemos")
public class UserMemo {
    @Id
    private String id;
    private String userId;
    private String content;

    public UserMemo(UserMemoDto memoUserDto){
        this.userId = memoUserDto.getUserId();
        this.content = memoUserDto.getContent();
    }
}

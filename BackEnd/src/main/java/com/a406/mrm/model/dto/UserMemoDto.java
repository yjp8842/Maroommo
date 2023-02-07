package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.UserMemo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserMemoDto {
    private String id;
    private String userId;
    private String content;

    public UserMemoDto(UserMemo userMemo){
        this.id = userMemo.getId();
        this.userId = userMemo.getUserId();
        this.content = userMemo.getContent();
    }
}

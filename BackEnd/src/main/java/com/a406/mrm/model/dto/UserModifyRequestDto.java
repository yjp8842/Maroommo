package com.a406.mrm.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModifyRequestDto {
    private String id;
    private String password;
    private String intro;
    private String nickname;
    private String name;

    public UserModifyRequestDto(String id, String intro, String nickname, String name){
        this.id = id;
        this.intro = intro;
        this.nickname = nickname;
        this.name = name;
    }
}

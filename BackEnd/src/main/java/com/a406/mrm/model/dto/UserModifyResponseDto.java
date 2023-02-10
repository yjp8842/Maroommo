package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModifyResponseDto {
    private String id;
    private String intro;
    private String nickname;
    private String name;
    private String profile;

    public UserModifyResponseDto(User user){
        this.id = user.getId();
        this.intro = user.getIntro();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.profile = user.getProfile();
    }
}

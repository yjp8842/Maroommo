package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;
    private String profile;
    private String intro;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.profile = user.getProfile();
        this.intro = user.getIntro();
    }
}

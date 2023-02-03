package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginResponseDto {
    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;
    private String profile;
    private String intro;
    private String memo;
    private String roles;
    private String provider;
    private String providerId;

    public UserLoginResponseDto(User user){
        this.id=user.getId();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.name=user.getName();
        this.nickname=user.getNickname();
        this.profile=user.getProfile();
        this.intro=user.getIntro();
        this.memo=user.getMemo();
        this.roles=user.getRoles();
        this.provider=user.getProvider();
        this.providerId=user.getProviderId();
    }
}

package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserJoinDto {
    public UserJoinDto(User user){
        this.id=user.getId();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.name=user.getName();
        this.nickname=user.getNickname();
    }
    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;
}

package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.UserHasRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;
    private String profile;
    private String intro;
    private String memo;
    private String roles; // ROLE_USER, ROLE_ADMIN
    private String provider;
    private String providerId;
    private List<UserHasRoom> rooms = new ArrayList<>();
}

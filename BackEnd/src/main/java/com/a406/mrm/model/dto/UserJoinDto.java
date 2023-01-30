package com.a406.mrm.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserJoinDto {
    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;
}

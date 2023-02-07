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
    private String profile;
}

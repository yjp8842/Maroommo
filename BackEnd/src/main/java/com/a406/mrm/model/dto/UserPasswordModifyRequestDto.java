package com.a406.mrm.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPasswordModifyRequestDto {
    private String id;
    private String password;
}

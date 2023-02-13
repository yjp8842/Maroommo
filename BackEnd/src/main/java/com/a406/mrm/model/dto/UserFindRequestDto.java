package com.a406.mrm.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserFindRequestDto {
    private String id;
    private String email;
    private String name;
}

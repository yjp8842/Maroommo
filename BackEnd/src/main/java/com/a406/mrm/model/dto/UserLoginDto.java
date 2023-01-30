package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginDto {
    public UserLoginDto(User user){
        this.id=user.getId();
        this.password=user.getPassword();
    }
    private String id;
    private String password;
}

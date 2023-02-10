package com.a406.mrm.model.dto;

import com.a406.mrm.model.entity.User;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinRequestDto {
    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;

    public User toUser(PasswordEncoder passwordEncoder){
        return User.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .email(email)
                .name(name)
                .nickname(nickname)
                .roles("ROLE_USER")
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(id, password);
    }
}

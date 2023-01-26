package com.a406.mrm.controller;

import com.a406.mrm.config.jwt.JwtProperties;
import com.a406.mrm.config.oauth.provider.GoogleUserInfo;
import com.a406.mrm.config.oauth.provider.OAuth2UserInfo;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwtCreateController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/oauth/jwt/google")
    public String jwtCreateGoogle(@RequestBody Map<String, Object> data){
        System.out.println("jwtCreate-google 실행됨");
        OAuth2UserInfo googleUser =
                new GoogleUserInfo((Map<String, Object>)data.get("profileObj"));

        User userEntity =
                userRepository.findById(googleUser.getProvider()+"_"+googleUser.getProviderId());

        if(userEntity == null) {
            User userRequest = User.builder()
                    .id(googleUser.getProvider()+"_"+googleUser.getProviderId())
                    .password(bCryptPasswordEncoder.encode("mrm구글로그인"))
                    .email(googleUser.getEmail())
                    .provider(googleUser.getProvider())
                    .providerId(googleUser.getProviderId())
                    .roles("ROLE_USER")
                    .build();

            userEntity = userRepository.save(userRequest);
        }

        String jwtToken = JWT.create()
                .withSubject(userEntity.getId())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME)) // 토큰 유효기간 (10분정도가 좋다나 뭐라나)
                .withClaim("id", userEntity.getId())
                .withClaim("username", userEntity.getName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;
    }


    @PostMapping("/oauth/jwt/facebook")
    public String jwtCreateFacebook(@RequestBody Map<String, Object> data){
        System.out.println("jwtCreate-facebook 실행됨");
        OAuth2UserInfo googleUser =
                new GoogleUserInfo((Map<String, Object>)data.get("profileObj"));

        User userEntity =
                userRepository.findById(googleUser.getProvider()+"_"+googleUser.getProviderId());

        if(userEntity == null) {
            User userRequest = User.builder()
                    .id(googleUser.getProvider()+"_"+googleUser.getProviderId())
                    .password(bCryptPasswordEncoder.encode("mrm구글로그인"))
                    .email(googleUser.getEmail())
                    .provider(googleUser.getProvider())
                    .providerId(googleUser.getProviderId())
                    .roles("ROLE_USER")
                    .build();

            userEntity = userRepository.save(userRequest);
        }

        String jwtToken = JWT.create()
                .withSubject(userEntity.getId())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME)) // 토큰 유효기간 (10분정도가 좋다나 뭐라나)
                .withClaim("id", userEntity.getId())
                .withClaim("username", userEntity.getName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;
    }
}

package com.a406.mrm.controller;

import com.a406.mrm.config.jwt.JwtProperties;
import com.a406.mrm.config.oauth.provider.FacebookUserInfo;
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

    private String createToken(OAuth2UserInfo oAuth2UserInfo, String providerName){
        User userEntity =
                userRepository.findById(oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId());

        if(userEntity == null) {
            User userRequest = User.builder()
                    .id(oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId())
                    .password(bCryptPasswordEncoder.encode("mrm"+providerName)) // mrm xx 로그인
                    .email(oAuth2UserInfo.getEmail())
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .roles("ROLE_USER")
                    .build();

            userEntity = userRepository.save(userRequest);
        }

        String jwtToken = JWT.create()
                .withSubject(userEntity.getId())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME)) // 토큰 유효기간
                .withClaim("id", userEntity.getId())
                .withClaim("username", userEntity.getName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;
    }

    @PostMapping("/oauth/jwt/google")
    public String jwtCreateGoogle(@RequestBody Map<String, Object> data){
        System.out.println("jwtCreate-google 실행됨");
        OAuth2UserInfo googleUser =
                new GoogleUserInfo((Map<String, Object>)data.get("profileObj"));
        return this.createToken(googleUser,"google");
    }

    @PostMapping("/oauth/jwt/facebook")
    public String jwtCreateFacebook(@RequestBody Map<String, Object> data){
        System.out.println("jwtCreate-facebook 실행됨");
        OAuth2UserInfo facebookUser =
                new FacebookUserInfo((Map<String, Object>)data.get("profileObj"));
        return this.createToken(facebookUser,"facebook");
    }
}

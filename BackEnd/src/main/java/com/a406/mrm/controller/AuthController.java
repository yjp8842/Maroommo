package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("join")
    public ResponseEntity<UserResponseDto> join(@RequestBody UserJoinRequestDto userJoinRequestDto) {
        System.out.println("[join] 호출");
        System.out.println(userJoinRequestDto);
        return ResponseEntity.ok(authService.signup(userJoinRequestDto));
    }

    // 여기서 지금 토큰 정보를 넘겨주고 있는데 그냥 유저 정보와 함께 반환해주면 끝이다
    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        System.out.println("[login] 호출");
        System.out.println(userLoginRequestDto);
        return ResponseEntity.ok(authService.login(userLoginRequestDto));
    }

    @PostMapping("reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        System.out.println("[reissue] 호출");
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
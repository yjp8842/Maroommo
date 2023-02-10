package com.a406.mrm.controller;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("join")
    public ResponseEntity<UserResponseDto> join(@RequestBody UserJoinRequestDto userJoinRequestDto) {
        System.out.println("[join] 호출");
        return ResponseEntity.ok(authService.signup(userJoinRequestDto));
    }

    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        System.out.println("[login] 호출");
        return ResponseEntity.ok(authService.login(userLoginRequestDto));
    }

    @PostMapping("reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        System.out.println("[reissue] 호출");
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
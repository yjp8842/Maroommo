package com.a406.mrm.controller;

import com.a406.mrm.config.jwt.SecurityUtil;
import com.a406.mrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("me")
    public ResponseEntity<?> findUserById() {
        System.out.println("findUserById 호출");
        return ResponseEntity.ok(userService.findUserById(SecurityUtil.getCurrentMemberId()));
    }

}

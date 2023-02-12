package com.a406.mrm.controller;

import com.a406.mrm.model.dto.UserLoginResponseDto;
import com.a406.mrm.service.EmailService;
import com.a406.mrm.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("oauth")
@RequiredArgsConstructor
public class OauthController {

    private final UserService userService;

    /**
     * @param user
     *          를 통해 소셜 로그인 후 유저 정보를 제공한다
     * @return user : 소셜 로그인 한 유저의 정보를 반환한다
     */
    @GetMapping("user")
    private ResponseEntity<Map<String, Object>> getUser(@AuthenticationPrincipal User user) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        UserLoginResponseDto userLoginResponseDto = null;
        String refreshToken = null;
        String userId = user.getUsername();

        try {
            userLoginResponseDto = userService.getLoginUser(userId);
            refreshToken = userService.getRefreshToken(userId);
            resultMap.put("user", userLoginResponseDto);
            resultMap.put("refreshToken", refreshToken);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}

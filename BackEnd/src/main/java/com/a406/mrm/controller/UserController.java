package com.a406.mrm.controller;

import com.a406.mrm.common.util.SecurityUtil;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final UserServiceImpl userService;
//    private final SecurityUtil securityUtil;

    @GetMapping("home")
    public String home(){
        String userId = this.getCurrentMemberId();
        return "<h1>"+userId+"</h1>";
    }

    public static String getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information.");
        }
        return authentication.getName();
    }

    @PostMapping("join")
    private ResponseEntity<Map<String, Object>> join(
            @RequestBody User user) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            if(!userService.checkExistsUser(user.getId()))	{
                userService.join(user);

                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
            else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}

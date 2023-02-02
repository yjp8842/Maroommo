package com.a406.mrm.controller;

import com.a406.mrm.config.auth.PrincipalDetails;
import com.a406.mrm.model.dto.UserLoginResponseDto;
import com.a406.mrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> loginPage(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/user")) { // 이전 페이지가 없거나 회원가입, 아이디 찾기 등 회원 관련 uri이면 무시
            request.getSession().setAttribute("prevPage", uri);
            resultMap.put("message", "success");
            resultMap.put("uri",uri);
        }
        else{
            resultMap.put("message", "fail");
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
//
//    @GetMapping("/join")
//    public String joinForm(){
//        return "/joinForm";
//    }

}

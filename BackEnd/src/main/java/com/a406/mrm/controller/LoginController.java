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

//@RestController
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String loginForm(){
        return "/loginForm";
    }

    @GetMapping("/findPassword")
    public String findPassword(){
        return "/findPassword";
    }

    @GetMapping("/join")
    public String joinForm(){
        return "/joinForm";
    }

}

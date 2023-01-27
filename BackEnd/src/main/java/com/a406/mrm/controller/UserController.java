package com.a406.mrm.controller;

import com.a406.mrm.config.auth.PrincipalDetails;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.service.EmailService;
import com.a406.mrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final UserService userService;
    private final EmailService emailService;


    /**
     *  회원가입 메서드
     *  아이디 중복 확인 후 비밀번호 암호화하여 DB에 저장
     */
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

    @PostMapping("/emailConfirm")
    public @ResponseBody String emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailService.sendMessage(email);
        System.out.println("이메일 전송 완료 : "+confirm);

        return confirm;
    }


    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    @GetMapping({ "", "/" })
    public @ResponseBody String index() {
        return "인덱스 페이지입니다.";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println("Principal : " + principal);
        System.out.println("OAuth2 : "+principal.getUser().getProvider());

        return "유저 페이지입니다.";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/findPassword")
    public String findPassword() {
        return "findPassword";
    }

    // getName을 통해 로그인한 아이디를 불러올 수 있다
    @GetMapping("/board")
    public @ResponseBody String board(Principal principal, Authentication authentication){

        System.out.println("principal : "+principal.getName());
        System.out.println("authentication : "+authentication.getName());

        return "로그인 성공";
    }

}

package com.a406.mrm.controller;

import com.a406.mrm.config.auth.PrincipalDetails;
import com.a406.mrm.model.dto.UserDto;
import com.a406.mrm.service.EmailService;
import com.a406.mrm.service.UserService;
import lombok.RequiredArgsConstructor;
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
     *  아이디 중복 확인 메서드
     *  동일한 아이디를 사용하는 유저가 존재하는지 확인한다
     *  해당 유저가 존재하지 않는다면 (message:SUCCESS) 반환
     *  존재한다면 (message:FAIL) 반환
     */
    @PostMapping("duplicate/id")
    private ResponseEntity<Map<String, Object>> existsId(
            @RequestBody String id) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            // 동일한 유저가 존재하지 않는다면
            if(!userService.existsByUserForId(id))	{
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
            // 동일한 유저가 존재한다면
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

    /**
     *  회원가입 메서드
     *  입력한 유저 정보를 바탕으로 회원가입을 진행한다
     */
    @PostMapping("join")
    private ResponseEntity<Map<String, Object>> join(
            @RequestBody UserDto userDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            userService.join(userDto);

            resultMap.put("message", SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  이메일 전송 메서드
     *  비밀번호 찾기 시 입력한 이메일 주소에 인증 코드를 전송
     */
    @PostMapping("emailConfirm")
    private @ResponseBody String emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailService.sendMessage(email);
        System.out.println("이메일 전송 완료 : "+confirm);

        return confirm;
    }

    /**
     *  아이디 찾기 메서드
     *  아이디 찾기 시 입력한 이름, 이메일을 바탕으로 유저를 찾는다
     *  해당되는 유저가 있을 경우 아이디와 (message:SUCCESS) 반환
     *  없을 경우 (message:FAIL) 반환
     */
    @PostMapping("help/id")
    private ResponseEntity<Map<String, Object>> findId(
            @RequestBody String name, String email) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            String userId = userService.findByUserForNameAndEmail(name,email);

            // 해당 유저가 존재한다면
            if(userId != null){

                int pos = 2;
                String str = "***";

                userId = userId.substring(0,pos) + str + str.substring(pos+str.length());

                resultMap.put("id", userId);
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

    /**
     *  비밀번호 찾기 메서드
     *  비밀번호 찾기 시 입력한 아이디, 이름, 이메일에 해당되는 유저가 있는지 확인
     *  해당되는 유저가 있을 경우 아이디와 (message:SUCCESS) 반환
     *  없을 경우 (message:FAIL) 반환
     */
    @PostMapping("help/pw")
    private ResponseEntity<Map<String, Object>> findPassword(
            @RequestBody String id, String name, String email) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            // 해당 유저가 존재한다면
            if(userService.existsByUserForIdAndNameAndEmail(id,name,email)){
                resultMap.put("id",id);
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

    /**
     *  비밀번호 변경 메서드
     *  유저의 비밀번호를 변경한다
     *  변경 성공 시 (message:SUCCESS) 반환
     */
    @PutMapping("help/pw")
    private ResponseEntity<Map<String, Object>> modifyPassword(
            @RequestBody String id, String password) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            userService.modifyPassword(id,password);

            resultMap.put("message", SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    // 테스트 용 mapping 함수들

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

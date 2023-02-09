package com.a406.mrm.controller;
;
import com.a406.mrm.model.dto.ScheduleResponseDto;
import com.a406.mrm.model.dto.UserJoinRequestDto;
import com.a406.mrm.model.dto.UserLoginResponseDto;
import com.a406.mrm.model.dto.UserModifyRequestDto;
import com.a406.mrm.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/user"})
@Api("User Controller API")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final UserService userService;
    private final EmailService emailService;
    private final ScheduleService scheduleService;

    /**
     *  회원가입 메서드
     *  입력한 유저 정보를 바탕으로 아이디 중복을 확인한 후 문제가 없다면 회원가입을 진행한다
     */
    @ApiOperation("User registration")
    @PostMapping
    private ResponseEntity<Map<String, Object>> join(
            @RequestBody @ApiParam("Join User Information") UserJoinRequestDto userJoinDto) {
        logger.info("[join] user:{}", userJoinDto);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            // 이미 가입한 아이디인지 확인한다
            // 존재하지 않는다면 회원가입을 진행시킨다
            if(!userService.existsByUserForId(userJoinDto.getId())){
                userService.join(userJoinDto);
                resultMap.put("isExist", false);
            }
            else{
                resultMap.put("isExist", true);
            }
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  아이디 찾기 메서드
     *  아이디 찾기 시 입력한 이름, 이메일을 바탕으로 유저를 찾는다
     *  해당되는 유저가 있을 경우 아이디와 (message:SUCCESS) 반환
     *  없을 경우 (message:FAIL) 반환
     */
    @ApiOperation("Find Id by name and email")
    @GetMapping("help/id")
    private ResponseEntity<Map<String, Object>> findId(
            @RequestParam @ApiParam("name Information to find id") String name,
            @RequestParam @ApiParam("email Information to find id") String email) {

        logger.info("[findId] name:{}, email:{}", name, email);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            String userId = userService.findByUserForNameAndEmail(name,email);

            // 해당 유저가 존재한다면
            if(userId != null){

                int pos = 2;
                String str = "***";

                userId = userId.substring(0,pos) + str + userId.substring(pos+str.length());

                resultMap.put("userId", userId);
                resultMap.put("isExist", true);
            }
            else{
                resultMap.put("userId", null);
                resultMap.put("isExist", false);
            }
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
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
    @ApiOperation("Find Password by id, name and email")
    @GetMapping("help/pw")
    private ResponseEntity<Map<String, Object>> findPassword(
            @RequestParam @ApiParam("id Information to find password") String id,
            @RequestParam @ApiParam("name Information to find password") String name,
            @RequestParam @ApiParam("email Information to find password") String email) {

        logger.info("[findPassword] id:{}, name:{}, email:{}", id, name, email);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            // 해당 유저가 존재한다면
            if(userService.existsByUserForIdAndNameAndEmail(id,name,email)){
                resultMap.put("userId",id);
            }
            else {
                resultMap.put("userId", null);
            }
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  이메일 전송 메서드
     *  비밀번호 찾기 시 입력한 이메일 주소에 인증 코드를 전송
     */
    @ApiOperation("Send Email With authentication Code")
    @GetMapping("help/{email}")
    private ResponseEntity<Map<String, Object>> sendEmail(
            @PathVariable @ApiParam("send email Information") String email) throws Exception {
        logger.info("[sendEmail] email:{}", email);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        String emailCode = null; // 이메일로 보낸 인증코드

        try {
            emailCode = emailService.sendMessage(email);

            resultMap.put("emailCode", emailCode);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  비밀번호 변경 메서드
     *  유저의 비밀번호를 변경한다
     */
    @ApiOperation("Modify Password")
    @PatchMapping("help/pw")
    private ResponseEntity<Map<String, Object>> modifyPassword(
            @RequestBody @ApiParam("id, password Information to modify password") UserModifyRequestDto user) {

        logger.info("[modifyPassword] id:{}, password:{}", user.getId(), user.getPassword());

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            userService.modifyPassword(user);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  로그인 실패 처리 메서드
     *  로그인 실패 시 에러 메시지를 반환한다
     *  프론트에서 해당 메시지를 출력해준다.
     */
    @ApiOperation("login Fail")
    @GetMapping("login/error")
    private ResponseEntity<Map<String, Object>> loginFail(
            @RequestParam @ApiParam("login fail message") String loginFailMessage) {
        logger.info("[loginFail] login Fail Message:{}", loginFailMessage);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        resultMap.put("loginFailMessage", loginFailMessage);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /** 
     *  로그인 성공 처리 메서드
     *  로그인 성공 시 유저 정보와 이전 페이지 url을 반환한다
     *  프론트에서는 이전 페이지 url이 비어있는지 여부를 확인하여 api를 요청한다
     *
     *  유저 정보를 모두 받아오는 작업을 해야한다
     */
    @ApiOperation("login success")
    @GetMapping("login/success")
    private ResponseEntity<?> loginSuccess(
            @RequestParam @ApiParam("login prevPage") String prevPage
            ,@RequestParam @ApiParam("login userId") String userId
//            ,@AuthenticationPrincipal PrincipalDetails principalDetails
            ){
        logger.info("[loginSuccess] prevPage:{}, userId:{}", prevPage, userId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        // /room/enter?code=1293676745
        boolean isPrev = false;

        if(prevPage.contains("/room/enter")){
            isPrev = true;
        }
        else{
            prevPage = null;
        }

        // 이전 페이지 요청이 있다면 isPrev=true, prevPage 존재
        // 없다면 isPrev=false, prevPage=""

        resultMap.put("isPrev", isPrev);
        resultMap.put("prevPage", prevPage);

        UserLoginResponseDto user = null;

        try {
            user = userService.getLoginUser(userId);
            resultMap.put("user",user);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  유저 정보 변경 메서드
     *  유저의 정보(intro, name, nickname, profile)를 변경한다
     */
    @ApiOperation("Modify user infomation")
    @PatchMapping
    private ResponseEntity<Map<String, Object>> modifyUserInfo(
            @RequestBody @ApiParam("user Information to modify") UserModifyRequestDto user) {

        logger.info("[modifyUserInfo] user:{}", user);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            // 유저가 존재한다면
            if(userService.existsByUserForId(user.getId())){
                userService.modify(user);
                resultMap.put("isModify", "success");
            }
            else{
                resultMap.put("isModify", "fail");
            }
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }





    //////////////////////////////
    //////// 테스트 용 코드 ////////
    //////////////////////////////

    /**
     *  유저들 정보 가져오기 메서드
     */
    @ApiOperation("get users info")
    @GetMapping("/all")
    private ResponseEntity<?> getUsers(){
        logger.info("[getUsers]");

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        List<UserLoginResponseDto> users = null;

        try {
            users = userService.getUserList();
            resultMap.put("usesr",users);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}

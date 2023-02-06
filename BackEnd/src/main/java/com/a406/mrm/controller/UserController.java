package com.a406.mrm.controller;
;
import com.a406.mrm.config.auth.PrincipalDetails;
import com.a406.mrm.model.dto.UserJoinRequestDto;
import com.a406.mrm.model.dto.UserLoginResponseDto;
import com.a406.mrm.model.dto.UserModifyRequestDto;
import com.a406.mrm.service.EmailService;
import com.a406.mrm.service.UserService;
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
import java.util.Map;

//@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping({"/user"})
@Api("User Controller API")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final UserService userService;
    private final EmailService emailService;


    /**
     *  로그인 정보 요청 메서드
     *  로그인 후 프론트에서 요청을 하면 유저 정보를 제공해줌
     *  프론트단에서 create 시 세션에 유저 정보가 없다면 로그인 유저 정보 요청을 한다
     */
    @ApiOperation("Request Login User Information")
    @GetMapping
    public ResponseEntity<Map<String, Object>> userInfo(
            @AuthenticationPrincipal PrincipalDetails principalDetails){

        logger.info("[userInfo] Request Login User Infomation");

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        String userId = principalDetails.getUsername();
        UserLoginResponseDto user = null;

        try {
            user = userService.getLoginUser(userId);
            resultMap.put("user",user);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    /**
     *  아이디 중복 확인 메서드
     *  동일한 아이디를 사용하는 유저가 존재하는지 확인한다
     *  해당 유저가 존재하지 않는다면 (message:SUCCESS) 반환
     *  존재한다면 (message:FAIL) 반환
     */
    @ApiOperation("Confirm ID duplication")
    @GetMapping("duplicate")
    private ResponseEntity<Map<String, Object>> existsId(
            @RequestParam @ApiParam("Confirm User ID") String id) {
        logger.info("[existsId] User Id:{}", id);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            // 동일한 유저가 존재하지 않는다면
            if(!userService.existsByUserForId(id))	{
                status = HttpStatus.ACCEPTED;
            }
            // 동일한 유저가 존재한다면
            else {
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  회원가입 메서드
     *  입력한 유저 정보를 바탕으로 회원가입을 진행한다
     *  (기본적으로 유저 정보는 아이디 중복 확인이 되어있다)
     */
    @ApiOperation("User registration")
    @PostMapping
    private ResponseEntity<Map<String, Object>> join(
            @RequestBody @ApiParam("Join User Information") UserJoinRequestDto userJoinDto) {
        logger.info("[join] Join User Information - user:{}", userJoinDto);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            userService.join(userJoinDto);

            status = HttpStatus.ACCEPTED;
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
        logger.info("[sendEmail] send email Information - email:{}", email);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String authCode = null; // 이메일로 보낸 인증코드

        try {
            authCode = emailService.sendMessage(email);

            resultMap.put("authCode", authCode);
            status = HttpStatus.ACCEPTED;
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

        logger.info("[findId] name, email Information to find id - name:{}, email:{}", name, email);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            String userId = userService.findByUserForNameAndEmail(name,email);

            // 해당 유저가 존재한다면
            if(userId != null){

                int pos = 2;
                String str = "***";

                userId = userId.substring(0,pos) + str + userId.substring(pos+str.length());

                resultMap.put("id", userId);
                status = HttpStatus.ACCEPTED;
            }
            else{
                resultMap.put("id", null);
                status = HttpStatus.ACCEPTED;
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

        logger.info("[findPassword] id, name, email Information to find password - id:{}, name:{}, email:{}", id, name, email);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            // 해당 유저가 존재한다면
            if(userService.existsByUserForIdAndNameAndEmail(id,name,email)){
                resultMap.put("id",id);
                status = HttpStatus.ACCEPTED;
            }
            else {
                resultMap.put("id", null);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     *  비밀번호 변경 메서드
     *  유저의 비밀번호를 변경한다
     *  변경 성공 시 (message:SUCCESS) 반환
     */
    @ApiOperation("Modify Password")
    @PatchMapping("help/pw")
    private ResponseEntity<Map<String, Object>> modifyPassword(
            @RequestBody @ApiParam("id, password Information to modify password") UserModifyRequestDto user) {

        logger.info("[modifyPassword] id, password Information to modify password - id:{}, password:{}", user.getId(), user.getPassword());

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            userService.modifyPassword(user.getId(),user.getPassword());
            status = HttpStatus.ACCEPTED;
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
     */
    @ApiOperation("login success")
    @GetMapping("login/success")
    private ResponseEntity<?> loginSuccess(
            @RequestParam @ApiParam("login prevPage") String prevPage
            ,@RequestParam @ApiParam("login userId") String userId
//            ,@AuthenticationPrincipal PrincipalDetails principalDetails
            ){
        logger.info("[loginSuccess] prevPage:{}", prevPage);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        // /room/enter?code=1293676745
        boolean isPrev = false;

        if(prevPage.contains("/room/enter")){
            isPrev = true;
        }
        else{
            prevPage = "";
        }

        // 이전 페이지 요청이 있다면 isPrev=true, prevPage 존재
        // 없다면 isPrev=false, prevPage=""

        resultMap.put("isPrev", isPrev);
        resultMap.put("prevPage", prevPage);

        System.out.println(userId);

        UserLoginResponseDto user = null;

        try {
            user = userService.getLoginUser(userId);
            resultMap.put("user",user);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}

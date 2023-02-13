package com.a406.mrm.controller;
;
import com.a406.mrm.model.dto.*;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api("User Controller API")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final UserService userService;
    private final EmailService emailService;

    /**
     * @param userLoginRequestDto
     *                      를 통해 로그인을 진행합니다
     * @return token : 유저 정보가 포함된 jwt token을 반환합니다
     * @return user : 유저의 자세한 정보를 반환합니다
     */
    @PostMapping("/login")
    private ResponseEntity<Map<String, Object>> login(
            @RequestBody UserLoginRequestDto userLoginRequestDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        TokenResponseDto tokenResponseDto = null;
        UserLoginResponseDto userLoginResponseDto = null;
        try {
            tokenResponseDto = userService.login(userLoginRequestDto);
            userLoginResponseDto = userService.getLoginUser(userLoginRequestDto.getId());
            resultMap.put("token", tokenResponseDto);
            resultMap.put("user", userLoginResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param userJoinDto
     *              를 통해 회원가입을 진행한다
     * @return x
     */
    @PostMapping("/signup")
    private ResponseEntity<Map<String, Object>> signup(
            @RequestBody UserJoinRequestDto userJoinDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            userService.signup(userJoinDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/reissue")
    private ResponseEntity<Map<String, Object>> reissue(
            @RequestBody TokenRequestDto tokenRequestDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        TokenResponseDto tokenResponseDto = null;
        try {
            tokenResponseDto = userService.reissue(tokenRequestDto);
            resultMap.put("token", tokenResponseDto);
        } catch (Exception e) {
            resultMap.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * @param userFindRequestDto
     *          을 통해 아이디를 찾는다
     * @return isExist : 아이디 중복 여부를 반환
     * @return userId : 중복된 아이디를 가공처리하여 반환
     */
    @ApiOperation("Find Id by name and email")
    @PostMapping("/help/id")
    private ResponseEntity<Map<String, Object>> findId(
            @RequestBody @ApiParam("name Information to find id") UserFindRequestDto userFindRequestDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            String userId = userService.findByUserForNameAndEmail(userFindRequestDto.getName(),userFindRequestDto.getEmail());

            // 해당 유저가 존재한다면
            if(userId != null){

                int pos = userId.length() / 2;
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
     * @param userFindRequestDto
     *          를 통해 중복된 아이디가 있는지 확인한다
     * @return userId : 중복된 아이디를 반환한다
     */
    @ApiOperation("Find Password by id, name and email")
    @PostMapping("/help/pw")
    private ResponseEntity<Map<String, Object>> findPassword(
            @RequestBody @ApiParam("id Information to find password") UserFindRequestDto userFindRequestDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            // 해당 유저가 존재한다면
            if(userService.existsByUserForIdAndNameAndEmail(userFindRequestDto.getId(),
                                                            userFindRequestDto.getName(),
                                                            userFindRequestDto.getEmail())){
                resultMap.put("userId",userFindRequestDto.getId());
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
     * @param email
     *          를 통해 이메일 인증 코드를 발송한다
     * @return emailCode : 전송한 이메일 인증 코드를 반환한다
     */
    @ApiOperation("Send Email With authentication Code")
    @GetMapping("/help/{email}")
    private ResponseEntity<Map<String, Object>> sendEmail(
            @PathVariable @ApiParam("send email Information") String email) {
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
     * @param userPasswordModifyRequestDto
     *              를 통해 입력한 비밀번호로 변경합니다
     * @return x
     */
    @ApiOperation("Modify Password")
    @PatchMapping("/help/pw")
    private ResponseEntity<Map<String, Object>> modifyPassword(
            @RequestBody UserPasswordModifyRequestDto userPasswordModifyRequestDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            userService.modifyPassword(userPasswordModifyRequestDto);
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

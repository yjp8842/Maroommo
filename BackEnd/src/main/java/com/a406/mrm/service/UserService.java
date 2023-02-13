package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    void signup(UserJoinRequestDto userJoinRequestDto) throws Exception;
    TokenResponseDto login(UserLoginRequestDto userLoginRequestDto) throws Exception;
    TokenResponseDto reissue(TokenRequestDto tokenRequestDto) throws Exception;
    UserLoginResponseDto getLoginUser(String userId) throws Exception;
    String getRefreshToken(String userId) throws Exception;
    String findByUserForNameAndEmail(String name, String email) throws Exception;
    boolean existsByUserForIdAndNameAndEmail(String id, String name, String email) throws Exception;
    void modifyPassword(UserPasswordModifyRequestDto userPasswordModifyRequestDto) throws Exception;
    UserModifyResponseDto modify(UserModifyRequestDto user, MultipartFile profile) throws Exception;
    List<UserLoginResponseDto> getUserList() throws Exception;
}

package com.a406.mrm.service;

import com.a406.mrm.model.dto.UserJoinRequestDto;
import com.a406.mrm.model.dto.UserLoginResponseDto;
import com.a406.mrm.model.dto.UserModifyRequestDto;
import com.a406.mrm.model.dto.UserModifyResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    void join(UserJoinRequestDto user) throws Exception;
    UserLoginResponseDto getLoginUser(String userId) throws Exception;
    boolean existsByUserForId(String userid) throws Exception;
    String findByUserForNameAndEmail(String name, String email) throws Exception;
    boolean existsByUserForIdAndNameAndEmail(String id, String name, String email) throws Exception;
    void modifyPassword(UserModifyRequestDto user) throws Exception;
    UserModifyResponseDto modify(UserModifyRequestDto user, MultipartFile profile) throws Exception;
    List<UserLoginResponseDto> getUserList() throws Exception;
}

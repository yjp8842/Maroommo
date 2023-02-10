package com.a406.mrm.service;


import com.a406.mrm.model.dto.UserResponseDto;

public interface UserService {

    UserResponseDto findUserById(String id);
}

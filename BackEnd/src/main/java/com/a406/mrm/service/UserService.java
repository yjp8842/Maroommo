package com.a406.mrm.service;

import com.a406.mrm.model.dto.UserJoinRequestDto;

public interface UserService {

    void join(UserJoinRequestDto user) throws Exception;
    boolean existsByUserForId(String userid) throws Exception;
    String findByUserForNameAndEmail(String name, String email) throws Exception;
    boolean existsByUserForIdAndNameAndEmail(String id, String name, String email) throws Exception;
    void modifyPassword(String id, String password) throws Exception;
}

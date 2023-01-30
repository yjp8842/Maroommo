package com.a406.mrm.service;

import com.a406.mrm.model.dto.UserDto;

public interface UserService {

    void join(UserDto user) throws Exception;
    boolean existsByUserForId(String userid) throws Exception;
    String findByUserForNameAndEmail(String name, String email) throws Exception;
    boolean existsByUserForIdAndNameAndEmail(String id, String name, String email) throws Exception;
    void modifyPassword(String id, String password) throws Exception;
}

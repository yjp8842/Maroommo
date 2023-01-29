package com.a406.mrm.service;

import com.a406.mrm.model.entity.User;

import java.util.Optional;

public interface UserService {

    void join(User user) throws Exception;
    boolean existsByUserForId(String userid) throws Exception;
    Optional<User> findByUserForNameAndEmail(String name, String email) throws Exception;
    boolean existsByUserForIdAndNameAndEmail(String id, String name, String email) throws Exception;
    void modifyPassword(String id, String password) throws Exception;
}

package com.a406.mrm.service;


import com.a406.mrm.model.entity.User;

public interface UserService {

    public void join(User user) throws Exception;
    public boolean checkExistsUser(String userid) throws Exception;
}

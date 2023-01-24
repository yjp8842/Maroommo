package com.a406.mrm.service;

import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String join(User user){

        boolean existUser = userRepository.existsById(user.getId());

        if(existUser) return null;

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 비밀번호 암호화

        user.setPassword(encPassword);

        System.out.println("회원가입 진행 : " + user);
        userRepository.save(user);

        return user.getId();
    }

}

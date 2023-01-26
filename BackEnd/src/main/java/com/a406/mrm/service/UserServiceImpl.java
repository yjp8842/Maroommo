package com.a406.mrm.service;

import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 유저 정보를 암호화하여 db에 저장한다
    @Override
    public void join(User user) throws Exception {        
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));// 비밀번호 암호화
        user.setRoles("ROLE_USER");
        userRepository.save(user);
    }

    // 유저 id를 조회하여 동일한 유저가 있는지 확인한다
    @Override
    public boolean checkExistsUser(String userid) throws Exception {
        return userRepository.existsById(userid);
    }


}

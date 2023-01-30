package com.a406.mrm.service;

import com.a406.mrm.model.dto.UserDto;
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
    public void join(UserDto userInfo) throws Exception {
        User user = User.builder()
                        .id(userInfo.getId())
                        .password(bCryptPasswordEncoder.encode(userInfo.getPassword()))
                        .email(userInfo.getEmail())
                        .name(userInfo.getName())
                        .nickname(userInfo.getNickname())
                        .roles("ROLE_USER")
                        .provider(userInfo.getProvider())
                        .providerId(userInfo.getProviderId())
                .build();

        userRepository.save(user);
    }

    // 유저 id를 조회하여 동일한 유저가 있는지 확인한다
    @Override
    public boolean existsByUserForId(String userid) throws Exception {
        return userRepository.existsById(userid);
    }

    // 이름과 이메일로 유저를 조회하여 유저 아이디를 반환
    @Override
    public String findByUserForNameAndEmail(String name, String email) throws Exception {
        String id = null;
        User user = userRepository.findByNameAndEmail(name,email).get();
        if(user != null) id = user.getId();
        return id;
    }

    // 아이디와 이름, 이메일을 조회하여 유저가 존재하는지 확인
    @Override
    public boolean existsByUserForIdAndNameAndEmail(String id, String name, String email) throws Exception {
        return userRepository.existsByIdAndNameAndEmail(id,name,email);
    }

    // 비밀번호 변경
    @Override
    public void modifyPassword(String id, String password) throws Exception {
        User user = userRepository.findById(id).get();
        user.setPassword(bCryptPasswordEncoder.encode(password));// 비밀번호 암호화
        userRepository.save(user);
    }


}



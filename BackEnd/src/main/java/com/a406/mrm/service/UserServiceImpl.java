package com.a406.mrm.service;

import com.a406.mrm.model.dto.UserResponseDto;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto findUserById(String id) {
        return userRepository.findById(id)
                .map(x->new UserResponseDto(x))
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }
}

package com.a406.mrm.service;

import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.model.entity.UserMemo;
import com.a406.mrm.repository.UserMemoRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMemoRepository userMemoRepository;
    private final ScheduleService scheduleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 유저 정보를 암호화하여 db에 저장한다
    @Override
    public void join(UserJoinRequestDto userInfo) throws Exception {
        User user = User.builder()
                .id(userInfo.getId())
                .password(bCryptPasswordEncoder.encode(userInfo.getPassword()))
                .email(userInfo.getEmail())
                .name(userInfo.getName())
                .nickname(userInfo.getNickname())
                .roles("ROLE_USER")
                .build();

        userRepository.save(user);
    }

    @Override
    public UserLoginResponseDto getLoginUser(String userId) throws Exception {
        UserLoginResponseDto userLoginResponseDtor = null;
        User user = userRepository.findById(userId).get();
        UserMemo userMemo = userMemoRepository.findByUserId(userId);
        List<ScheduleResponseDto> schedules = scheduleService.getSchedule(userId);

        if(user != null){
            userLoginResponseDtor = new UserLoginResponseDto(user, userMemo,schedules);
        }
        return userLoginResponseDtor;
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
    public void modifyPassword(UserModifyRequestDto userDto) throws Exception {
        User user = userRepository.findById(userDto.getId()).get();
        if(user != null){
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));// 비밀번호 암호화
            userRepository.save(user);
        }
    }

    @Override
    public UserModifyResponseDto modify(UserModifyRequestDto userDto, MultipartFile profile) throws Exception {
        User user = userRepository.findById(userDto.getId()).get();
        UserModifyResponseDto userModifyResponseDto = null;
        String uuid =  null;

        if(user != null){
            user.setIntro(userDto.getIntro());
            user.setName(userDto.getName());
            user.setNickname(userDto.getNickname());
            // 파일 저장
            if(profile != null){
                uuid = UUID.randomUUID().toString()+"."+profile.getOriginalFilename().substring(profile.getOriginalFilename().lastIndexOf(".")+1);
                String absPath = "/img_dir/"+uuid;
                try {
                    profile.transferTo(new File(absPath));
                    user.setProfile(uuid);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            userModifyResponseDto = new UserModifyResponseDto(userRepository.save(user));
        }

        return userModifyResponseDto;
    }

    @Override
    public List<UserLoginResponseDto> getUserList() throws Exception {
        List<UserLoginResponseDto> userList
                = userRepository.findAll()
                .stream()
                .map(x -> {
                    try {
                        return new UserLoginResponseDto(x, userMemoRepository.findByUserId(x.getId()), scheduleService.getSchedule(x.getId()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        return userList;
    }


}



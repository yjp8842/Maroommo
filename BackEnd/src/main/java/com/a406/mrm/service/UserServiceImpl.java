package com.a406.mrm.service;

import com.a406.mrm.config.jwt.TokenProvider;
import com.a406.mrm.model.dto.*;
import com.a406.mrm.model.entity.RefreshToken;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.model.entity.UserMemo;
import com.a406.mrm.repository.RefreshTokenRepository;
import com.a406.mrm.repository.UserMemoRepository;
import com.a406.mrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final UserMemoRepository userMemoRepository;
    private final ScheduleService scheduleService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final String DATE_FORMAT = "yyyy-MM-dd";
    // 유저 정보를 암호화하여 db에 저장한다
    @Override
    @Transactional
    public void signup(UserJoinRequestDto userJoinRequestDto) throws Exception {

        if (userRepository.existsById(userJoinRequestDto.getId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        User user = User.builder()
                .id(userJoinRequestDto.getId())
                .password(passwordEncoder.encode(userJoinRequestDto.getPassword()))
                .email(userJoinRequestDto.getEmail())
                .name(userJoinRequestDto.getName())
                .nickname(userJoinRequestDto.getNickname())
                .roles("ROLE_USER")
                .build();

        userRepository.save(user);
    }

    @Override
    @Transactional
    public TokenResponseDto login(UserLoginRequestDto userLoginRequestDto) throws Exception {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = userLoginRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenResponseDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Override
    @Transactional
    public TokenResponseDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenResponseDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    @Override
    @Transactional
    public UserLoginResponseDto getLoginUser(String userId) throws Exception {
        UserLoginResponseDto userLoginResponseDto = null;
        User user = userRepository.findById(userId).get();
        UserMemo userMemo = userMemoRepository.findByUserId(userId);
        List<ScheduleResponseDto> schedules = scheduleService.getSchedule(userId);

        if(user != null){
            userLoginResponseDto = new UserLoginResponseDto(user, userMemo,schedules,DATE_FORMAT);
        }
        return userLoginResponseDto;
    }

    @Override
    @Transactional
    public String getRefreshToken(String userId) throws Exception{
        RefreshToken refreshToken = refreshTokenRepository.findByKey(userId)
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        return refreshToken.getValue();
    }

    // 이름과 이메일로 유저를 조회하여 유저 아이디를 반환
    @Override
    @Transactional
    public String findByUserForNameAndEmail(String name, String email) throws Exception {
        String id = null;
        User user = userRepository.findByNameAndEmail(name,email).get();
        if(user != null) id = user.getId();
        return id;
    }

    // 아이디와 이름, 이메일을 조회하여 유저가 존재하는지 확인
    @Override
    @Transactional
    public boolean existsByUserForIdAndNameAndEmail(String id, String name, String email) throws Exception {
        return userRepository.existsByIdAndNameAndEmail(id,name,email);
    }

    // 비밀번호 변경
    @Override
    @Transactional
    public void modifyPassword(UserPasswordModifyRequestDto userPasswordModifyRequestDto) throws Exception {
        User user = userRepository.findById(userPasswordModifyRequestDto.getId()).get();

        if(user != null) {
            user.setPassword(passwordEncoder.encode(userPasswordModifyRequestDto.getPassword()));// 비밀번호 암호화
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public List<UserLoginResponseDto> getUserList() throws Exception {
        List<UserLoginResponseDto> userList
                = userRepository.findAll()
                .stream()
                .map(x -> {
                    try {
                        return new UserLoginResponseDto(x, userMemoRepository.findByUserId(x.getId()), scheduleService.getSchedule(x.getId()),DATE_FORMAT);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        return userList;
    }


}



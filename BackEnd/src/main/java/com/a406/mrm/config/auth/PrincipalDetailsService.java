package com.a406.mrm.config.auth;

import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// 시큐리티 설정에서 loginProcessingUrl()
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 프론트에서 넘어온 username와 loadUserByUsername의 username의 파라미터 이름이 같아야한다
    // 시큐리티 세션
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

//        return userRepository.findByUserEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Optional<User> loginUser = userRepository.findById(id);
        User user = null;

        if(loginUser == null) {
            System.out.println("로그인하지 못했습니다");
            throw new UsernameNotFoundException("Not Found account");
        }
        else{
            user = loginUser.get();
            System.out.println("User:"+user);
            return new PrincipalDetails(user);
        }
    }
}

package com.a406.mrm.config.auth;

import com.test.jwt.model.User;
import com.test.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// localhost:8080/login 으로 요청이 오면 동작을 한다
// 하지만 formlogin을 disable 했으니 동작을 안한다
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername : "+id);

        User loginUser = userRepository.findById(id);

        return new PrincipalDetails(loginUser);
    }
}

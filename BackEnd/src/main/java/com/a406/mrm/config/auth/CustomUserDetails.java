package com.a406.mrm.config.auth;

import com.a406.mrm.model.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

// 시큐리티가 /login 요청을 보고 로그인을 대신 진행해준다
// 로그인 진행 완료시 시큐리티 세션이 만들어진다
// Authentication 객체에 유저 정보를 저장하여 시큐리티 세션에 저장
@Data
public class CustomUserDetails implements UserDetails, OAuth2User {

    private User user;
    private Map<String, Object> attributes;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public CustomUserDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // 해당 user의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
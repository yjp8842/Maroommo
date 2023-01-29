package com.a406.mrm.config;

import com.a406.mrm.common.handler.AuthFailureHandler;
import com.a406.mrm.common.handler.AuthSuccessHandler;
import com.a406.mrm.config.auth.PrincipalDetails;
import com.a406.mrm.config.auth.PrincipalDetailsService;
import com.a406.mrm.config.oauth.PrincipalOauth2UserService;
import com.a406.mrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 로그인 과정에서 password를 가로챌 때 해당 해쉬로 암호화해서 비교한다
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService).passwordEncoder(encodePwd());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
            .antMatchers("/room/**").authenticated() // room에 입장하려면 권한이 있어야함
            .anyRequest().permitAll()
        .and()
            .formLogin()
            .usernameParameter("id") // 유저 id 파라미터를 username->id로 변경
            .loginPage("/loginForm") // 로그인 페이지는 해당 주소이며
            .loginProcessingUrl("/loginProc") // 로그인 요청 url이 들어오면 시큐리티가 대신 로그인 진행
            .successHandler(authSuccessHandler) // 로그인 성공시 처리할 핸들러
            .failureHandler(authFailureHandler) // 로그인 실패시 처리할 핸들러
            .defaultSuccessUrl("/main") // 로그인 후 디폴트 페이지로 가짐
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginForm") // 로그아웃 성공시 해당 주소로 이동
                .deleteCookies("JSESSIONID","remember-me") // 세션, 쿠키 삭제
                .permitAll()
                .and()
                .sessionManagement()
            .maximumSessions(1) // 세션 최대 허용 수 1 (-1이면 무제한 세션 허용)
            .maxSessionsPreventsLogin(false) // true:중복로그인막음 / false:이전로그인세션해제
            .expiredUrl("/login?error=true&exception=Have been attempted to login from a new place. or sesseion expired") // 세션 만료시 이동할 페이지
        .and()
            .and().rememberMe() // 로그인 유지
            .alwaysRemember(false) // 항상 기억할 것인지
            .tokenValiditySeconds(43200) // 12시간 유지(초 단위)
            .rememberMeParameter("remember-me")
        .and() // 소셜 로그인을 위한 설정
            .oauth2Login()
            .loginPage("/loginForm")
            .userInfoEndpoint()
            .userService(principalOauth2UserService);
    }
}
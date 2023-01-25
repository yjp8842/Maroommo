package com.a406.mrm.config;

import com.test.jwt.config.jwt.JwtAuthenticationFilter;
import com.test.jwt.config.jwt.JwtAuthorizationFilter;
import com.test.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsConfig corsConfig;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 만들지 않겠다.
            .and()

                .addFilter(corsConfig.corsFilter()) // 로그인 인증이 있을 경우 필터에 등록한다

                .formLogin().disable() // 기존 oauth 로그인 방식을 사용하지 않겠다
                .httpBasic().disable()

                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager를 던져줘야함
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))

                .authorizeRequests()
                .antMatchers("/room/**").authenticated()
                .anyRequest().permitAll() // 로그인을 제외한 모든 요청은 권한이 필요
;
    }
}

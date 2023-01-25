package com.a406.mrm.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.jwt.config.auth.PrincipalDetails;
import com.test.jwt.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 라는 필터
// /login 요청 시 username, password를 전송하면 해당 필터가 동작한다
// 근데 formlogin을 disable 했으니 동작을 안한다
// 따라서 새로운 필터를 만들어서 시큐리티에 필터 등록을 하자
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // 로그인 요청이 오면 로그인 시도를 위해서 실행되는 함수이다
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        System.out.println("JwtAuthenticationFilter 로그인 시도 중...");

        ObjectMapper om = new ObjectMapper();
        LoginRequestDto loginRequestDto = null;
        try {
            // 1. 아이디, 패스워드를 받아서
            loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // 2. 정상적인 로그인 시도를 한다
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getId(),
                        loginRequestDto.getPassword());

        // authenticationManager으로 로그인 시도 -> PrincipalDetailsService의 loadUserByUserName()이 실행된다
        // authentication에는 내 로그인 정보가 담김
        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        // 3. PrincipalDetails을 세션에 담고 (권한 관리를 안할거면 담지 않아도 된다)=> 로그인이 되었다는 의미
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println(principalDetails.getUsername());

        // 4. JWT 토큰을 만들어서 응답을 해준다
        // authentication 객체가 session 영역에 저장됨 => 권한 처리 때문에 세션에 저장하는 것
        return authentication;
    }

    // attemptAuthentication가 실행 후 인증이 정상적이라면 해당 함수가 실행된다
    // JWT 토큰을 만들어서 request 요청한 사용자에게 jwt 토큰을 response한다
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetailis.getUsername()) // 토큰 제목
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME)) // 토큰 유효기간 (10분정도가 좋다나 뭐라나)
                .withClaim("id", principalDetailis.getUser().getId())
                .withClaim("username", principalDetailis.getUser().getName())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
    }
}

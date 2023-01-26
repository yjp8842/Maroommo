package com.a406.mrm.config.jwt;


import com.a406.mrm.config.auth.PrincipalDetails;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 시큐리티가 가지고 있는 필터 중 BasicAuthentication 이 있다
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음
// 만약에 권한, 인증이 필요한 주소가 아니라면 필터를 타지 않는다
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository){
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    // 인증이나 권한이 필요한 주소 요청이 있을 경우 해당 필터를 거치게 된다
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("인증이나 권한이 필요한 주소가 요청됨 - (JwtAuthorizationFilter 필터 호출)");

        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println("jwtHeader : "+jwtHeader);

        // header가 있는지 검증
        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        // jwt 토큰을 검증해서 정상적인 사용자인지 확인한다
        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        // 서명을 하여 user Id를 가져온다
        String userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                .verify(jwtToken).getClaim("id").asString();

        // 서명 정상 완료
        if(userId!=null){
            User user = userRepository.findById(userId);

            PrincipalDetails principalDetails = new PrincipalDetails(user);

            // jwt 토큰 서명을 통해서 authentication 객체를 만들어준다
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 시큐리티 세션에 접근하여 authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request,response);
        }
    }
}


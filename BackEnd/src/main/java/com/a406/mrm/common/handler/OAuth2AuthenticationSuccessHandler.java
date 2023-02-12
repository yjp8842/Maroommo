package com.a406.mrm.common.handler;

import com.a406.mrm.common.util.CookieUtil;
import com.a406.mrm.config.jwt.TokenProvider;
import com.a406.mrm.model.dto.TokenResponseDto;
import com.a406.mrm.model.entity.RefreshToken;
import com.a406.mrm.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // redirect 할 url을 지정해준다
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String targetUrl = "http://localhost:3000/oauth2/social/success";
//        String targetUrl = "/oauth2/social/success";

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenResponseDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        int cookieMaxAge = tokenProvider.getExpiration().intValue() / 60;

        CookieUtil.deleteCookie(request, response, "refreshToken");
        CookieUtil.addCookie(response, "refreshToken", tokenDto.getRefreshToken(), cookieMaxAge);

        CookieUtil.deleteCookie(request, response, "grantType");
        CookieUtil.addCookie(response, "grantType", tokenDto.getGrantType(), cookieMaxAge);

        CookieUtil.deleteCookie(request, response, "accessTokenExpiresIn");
        CookieUtil.addCookie(response, "accessTokenExpiresIn", Long.toString(tokenDto.getAccessTokenExpiresIn()), cookieMaxAge);

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("accessToken", tokenDto.getAccessToken())
                .build().toUriString();
    }
}
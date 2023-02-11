package com.a406.mrm.common.handler;

import com.a406.mrm.common.CookieUtil;
import com.a406.mrm.config.jwt.TokenProvider;
import com.a406.mrm.model.dto.TokenDto;
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
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        int cookieMaxAge = tokenProvider.getExpiration().intValue() / 60;

        CookieUtil.deleteCookie(request, response, "refresh_token");
        CookieUtil.addCookie(response, "refresh_token", tokenDto.getRefreshToken(), cookieMaxAge);

        CookieUtil.deleteCookie(request, response, "grant_type");
        CookieUtil.addCookie(response, "grant_type", tokenDto.getGrantType(), cookieMaxAge);

        CookieUtil.deleteCookie(request, response, "access_token_expires_in");
        CookieUtil.addCookie(response, "access_token_expires_in", Long.toString(tokenDto.getAccessTokenExpiresIn()), cookieMaxAge);

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("access_token", tokenDto.getAccessToken())
                .build().toUriString();
    }
}
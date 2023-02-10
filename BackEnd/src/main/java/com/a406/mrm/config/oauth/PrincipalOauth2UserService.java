package com.a406.mrm.config.oauth;

import com.a406.mrm.config.auth.CustomUserDetails;
import com.a406.mrm.config.oauth.provider.GoogleUserInfo;
import com.a406.mrm.config.oauth.provider.KakaoUserInfo;
import com.a406.mrm.config.oauth.provider.NaverUserInfo;
import com.a406.mrm.config.oauth.provider.OAuth2UserInfo;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncode = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(PrincipalOauth2UserService.class);

    // userRequest 는 code를 받아서 accessToken을 응답 받은 객체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); //  회원 프로필 조회

        // code를 통해 구성한 정보
        logger.info("userRequest clientRegistration : " + userRequest.getClientRegistration());
        // token을 통해 응답받은 회원정보
        logger.info("oAuth2User : " + oAuth2User);

        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        // Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            logger.info("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
//        else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
//            logger.info("페이스북 로그인 요청");
//            System.out.println("페이스북 로그인 요청");
//            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
//        }
        else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            logger.info("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }
        else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            logger.info("카카오톡 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes());
        }else {
            logger.info("지원하지 않는 로그인 요청입니다");
        }

        Optional<User> userOptional =
                userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            // user가 존재하면 update 해주기
            user.setEmail(oAuth2UserInfo.getEmail());
            user.setName(oAuth2UserInfo.getName());
            userRepository.save(user);
        } else {
            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            user = User.builder()
                    .id(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
                    .password(bCryptPasswordEncode.encode(oAuth2UserInfo.getProvider()+"-mrm"))
                    .email(oAuth2UserInfo.getEmail())
                    .name(oAuth2UserInfo.getName())
                    .nickname(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId().substring(0,5))
                    .roles("ROLE_USER")
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
            userRepository.save(user);
        }

        return new CustomUserDetails(user, oAuth2User.getAttributes());
    }
}

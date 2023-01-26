package com.a406.mrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 한다
        config.addAllowedOrigin("*"); // 모든 ip의 응답 허용
        config.addAllowedHeader("*"); // 모든 header의 응답 허용
        config.addAllowedMethod("*"); // 모든 post,get,put,delete,patch 요청 허용

        // 해당 url로 들어오는 모든 주소는 해당 config를 따라야 한다
        source.registerCorsConfiguration("/user/**", config);

        return new CorsFilter(source);
    }

}
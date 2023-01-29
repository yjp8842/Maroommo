package com.a406.mrm.config;


import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 머스테치를 사용하기 위한 설정
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MustacheViewResolver resolver = new MustacheViewResolver();

        resolver.setCharset("UTF-8"); // view의 인코딩
        resolver.setContentType("text/html;charset=UTF-8"); // 던지는 data는 text/html 형식에 utf-8 인코딩 형식
        resolver.setPrefix("classpath:/templates/"); //
        resolver.setSuffix(".html"); // 머스테치가 인식할 수 있게 suffix를 html로 변경

        registry.viewResolver(resolver);
    }


}

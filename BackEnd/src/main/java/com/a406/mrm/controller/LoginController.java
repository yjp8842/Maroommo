package com.a406.mrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {

        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/user")) { // 이전 페이지가 없거나 회원가입, 아이디 찾기 등 회원 관련 uri이면 무시
            request.getSession().setAttribute("prevPage", uri);
        }

        System.out.println("[loginPage] 로그인 페이지 요청 : "+uri+", "+request.getSession().getAttribute("prevPage"));
        return "/loginForm";
    }

    @GetMapping("/join")
    public String joinForm(){
        return "/joinForm";
    }

}

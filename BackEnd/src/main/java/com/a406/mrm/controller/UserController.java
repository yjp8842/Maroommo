package com.a406.mrm.controller;

import com.a406.mrm.config.auth.PrincipalDetails;
import com.a406.mrm.model.entity.User;
import com.a406.mrm.repository.UserRepository;
import com.a406.mrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Iterator;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({ "", "/" })
    public @ResponseBody String index() {
        return "인덱스 페이지입니다.";
    }

    @GetMapping("/userinfo")
    public @ResponseBody String userinfo(){
        System.out.println();
        return "유저 정보 페이지입니다";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println("Principal : " + principal);
        System.out.println("OAuth2 : "+principal.getUser().getProvider());
//        // iterator 순차 출력 해보기
//        Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
//        while (iter.hasNext()) {
//            GrantedAuthority auth = iter.next();
//            System.out.println(auth.getAuthority());
//        }

        return "유저 페이지입니다.";
    }

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        System.out.println("");
        return "로그인 실패";
    }

    @PostMapping("/join")
    public @ResponseBody String join(User user) {

        String isJoin = userService.join(user);
        System.out.println("회원가입 :"+isJoin);

        return "/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    // getName을 통해 로그인한 아이디를 불러올 수 있다
    @GetMapping("/board")
    public @ResponseBody String board(Principal principal, Authentication authentication){
//          User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//          System.out.println("로그인 유저 : "+activeUser);

        System.out.println("principal : "+principal.getName());
        System.out.println("authentication : "+authentication.getName());

        return "로그인 성공";
    }

}

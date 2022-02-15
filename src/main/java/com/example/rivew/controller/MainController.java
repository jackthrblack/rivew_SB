package com.example.rivew.controller;

import com.example.rivew.dto.KakaoDTO;
import com.example.rivew.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public  String index(){
        return "index";
    }

    @Autowired
    private KakaoService ks;

    @GetMapping("/kakaologin")
    public String login(@RequestParam(value = "code", required = false) String code, HttpSession session)throws Exception {
        System.out.println("#########" + code);
        String access_Token = ks.getAccessToken(code);
        // 2번 인증코드로 토큰 전달
         Long userInfo = ks.getUserInfo(access_Token);

        System.out.println("login info : " + userInfo.toString());

        session.invalidate();
        // 위 코드는 session객체에 담긴 정보를 초기화 하는 코드.

        // 위 2개의 코드는 닉네임과 이메일을 session객체에 담는 코드
        // jsp에서 ${sessionScope.kakaoN} 이런 형식으로 사용할 수 있다.

        // 리턴값은 용도에 맞게 변경하세요~
        return "index";
    }

    @RequestMapping(value="/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        ks.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        mav.setViewName("index");
        return mav;
    }
}

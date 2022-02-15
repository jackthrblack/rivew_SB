package com.example.rivew.controller;

import com.example.rivew.dto.KakaoDTO;
import com.example.rivew.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class MainController {

    @GetMapping("/")
    public  String index(){
        return "index";
    }

  /*  @Autowired
    private KakaoService ks;

    @GetMapping("/kakaologin")
    public String login(@RequestParam(value = "code", required = false) String code, HttpSession session)throws Exception {
        System.out.println("#########" + code);
        String access_Token = ks.getAccessToken(code);
        // 2번 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = ks.getUserInfo(access_Token);

        System.out.println("login info : " + userInfo.toString());

        if(userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("accessToken", access_Token);
        }

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
    }*/
}

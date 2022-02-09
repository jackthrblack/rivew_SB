package com.example.rivew.controller;

import com.example.rivew.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class KakaoController {

   /* @Autowired
    private KakaoService ks;

    @RequestMapping(value="/kakaologin")
    public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        // 1번 인증코드 요청 전달
        String accessToken = ks.getAccessToken(code);
        // 2번 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = ks.getUserInfo(accessToken);

        System.out.println("login info : " + userInfo.toString());

        if(userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("accessToken", accessToken);
        }
        mav.addObject("userId", userInfo.get("email"));
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value="/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        ks.kakaoLogout((String)session.getAttribute("accessToken"));
        session.removeAttribute("accessToken");
        session.removeAttribute("userId");
        mav.setViewName("index");
        return mav;
    }*/
}

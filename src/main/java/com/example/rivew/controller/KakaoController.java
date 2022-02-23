package com.example.rivew.controller;

import com.example.rivew.dto.KakaoDTO;
import com.example.rivew.dto.KakaoDetailDTO;
import com.example.rivew.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class KakaoController {

    @Autowired
    private KakaoService ks;

    @GetMapping("/kakaologin")
    public String login(Model model, @RequestParam(value = "code", required = false) String code, KakaoDTO kakaoDTO, HttpSession session)throws Exception {
        System.out.println("#########" + code);
        String access_Token = ks.getAccessToken(code);
        // 2번 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = ks.getUserInfo(access_Token,kakaoDTO);
        List<KakaoDetailDTO> kakaoList = ks.findAll();
        model.addAttribute("kakao", kakaoList);
        System.out.println("a;wghv;oraseuhsuo"+kakaoList.toString());
        System.out.println("login info : " + userInfo.toString());

        if(userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("accessToken", access_Token);
        }

        return "index3";
    }

    @RequestMapping(value="/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        ks.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        mav.setViewName("index3");
        return mav;
    }
}

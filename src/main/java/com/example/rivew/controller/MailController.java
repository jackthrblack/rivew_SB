package com.example.rivew.controller;

import com.example.rivew.dto.*;
import com.example.rivew.entity.KakaoEntity;
import com.example.rivew.service.KakaoService;
import com.example.rivew.service.MailService;
import com.example.rivew.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mas;
    private final KakaoService ks;
    private final MemberService ms;

    @GetMapping("/mail")
    public String dispMail(){
        System.out.println("bgbg2222222");
        return "/mail/mailSend";
    }

    @PostMapping("/mailSend")
    public String execMail(HttpSession session, MailDTO mailDTO, Model model, MailCheckDTO mailCheckDTO, MemberDetailDTO memberDetailDTO){
        System.out.println("bgbg2222222");
        Long email = mas.mailsend(mailDTO, mailCheckDTO);

        model.addAttribute("check", mailCheckDTO);
        model.addAttribute("email", mailDTO);

        System.out.println(memberDetailDTO.toString());
        if(memberDetailDTO.getMemberEmail().equals(mailDTO.getMemberEmail())){
            Long Id=ms.findByMemberId(memberDetailDTO.getMemberEmail());
            session.setAttribute("memberId", Id);
            model.addAttribute("member", memberDetailDTO);
        }
        return "/mail/mailcode";
    }
}

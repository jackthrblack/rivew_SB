package com.example.rivew.controller;

import com.example.rivew.dto.*;
import com.example.rivew.entity.KakaoEntity;
import com.example.rivew.service.KakaoService;
import com.example.rivew.service.MailService;
import com.example.rivew.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/pwMailCheck")
    public @ResponseBody
    String pwMailCheck(@RequestParam("memberEmail") String memberEmail){

        String result = ms.pwMailChekc(memberEmail);
        return result;
    }

    @PostMapping("/mailSend")
    public String execMail(HttpSession session, MailDTO mailDTO, Model model, MailCheckDTO mailCheckDTO, MemberDetailDTO memberDetailDTO){
        System.out.println("bgbg2222222");
        MemberDetailDTO Id = ms.findByEmail(memberDetailDTO.getMemberEmail());
        Long Id2 = Id.getMemberId();
        System.out.println(Id.toString());
        session.setAttribute("memberId", Id2);

        System.out.println("asdfasdfasdfdsfgrebfdewf"+Id2);
            Long email = mas.mailsend(mailDTO, mailCheckDTO);

            model.addAttribute("check", mailCheckDTO);
            model.addAttribute("email", mailDTO);
            model.addAttribute("member", memberDetailDTO);

        System.out.println(memberDetailDTO.toString());

        return "/mail/mailcode";
    }


}

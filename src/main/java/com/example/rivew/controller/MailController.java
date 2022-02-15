package com.example.rivew.controller;

import com.example.rivew.dto.MailCheckDTO;
import com.example.rivew.dto.MailDTO;
import com.example.rivew.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService ms;

    @GetMapping("/mail")
    public String dispMail(){
        System.out.println("bgbg2222222");
        return "/mail/mailSend";
    }

    @PostMapping("/mailSend")
    public String execMail(MailDTO mailDTO, Model model, MailCheckDTO mailCheckDTO){
        System.out.println("asdfadsfasdfsadfadsfa");
        Long email = ms.mailsend(mailDTO, mailCheckDTO);

        model.addAttribute("email", mailCheckDTO);
        return "/mail/mailcode";
    }
}

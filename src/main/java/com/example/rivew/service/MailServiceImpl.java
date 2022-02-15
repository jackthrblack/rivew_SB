package com.example.rivew.service;

import com.example.rivew.dto.MailCheckDTO;
import com.example.rivew.dto.MailDTO;
import com.example.rivew.entity.MailEntity;
import com.example.rivew.repository.MailRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailRepository mr;

    private JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "이메일에 보낼 주소";

    public Long mailsend(MailDTO mailDTO, MailCheckDTO mailCheckDTO){
        Random random = new Random(); // 난수 생성
        String key=""; // 인증번호
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getMemberEmail());

        for(int i =0; i<3; i++){
            int index = random.nextInt(25)+65;
            key+=(char)index;
        }
        int numIndex = random.nextInt(9999)+1000;

        key += numIndex;
        message.setSubject("인증번호 입력을 위한 메일 전송");
        message.setText("인증번호 : " + key);
        //message.setText(key);
        /*message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());*/
        javaMailSender.send(message);
        mailCheckDTO.setEmailCheck(key);
        MailEntity mailEntity = MailEntity.saveMailCode(mailCheckDTO);
        return mr.save(mailEntity).getId();
       // return key;
    }
}

package com.example.rivew.service;

import com.example.rivew.dto.MailCheckDTO;
import com.example.rivew.dto.MailDTO;

public interface MailService {
    Long mailsend(MailDTO mailDTO, MailCheckDTO mailCheckDTO);
}

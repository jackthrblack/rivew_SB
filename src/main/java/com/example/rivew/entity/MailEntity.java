package com.example.rivew.entity;

import com.example.rivew.dto.MailCheckDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="email")
public class MailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emailId")
    private Long Id;

    @Column
    private String emailCheck;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private MemberEntity memberEntity;

    public static MailEntity saveMailCode(MailCheckDTO mailCheckDTO) {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setEmailCheck(mailCheckDTO.getEmailCheck());
        return mailEntity;
    }
}

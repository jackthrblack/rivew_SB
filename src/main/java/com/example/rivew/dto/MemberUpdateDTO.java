package com.example.rivew.dto;

import lombok.Data;

@Data
public class MemberUpdateDTO {

    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

}

package com.example.rivew.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginDTO {

    @NotBlank(message = "입력해주세요")
    private String memberEmail;
    @NotBlank(message = "입력해주세요")
    private String memberPassword;
}

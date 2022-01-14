package com.example.rivew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSaveDTO {

    @NotBlank(message = "꼭 써!")
    private String memberEmail;
    @NotBlank(message = "이것도 꼭 써!")
    @Length(min=2, max=10, message = "2~10자로 써!")
    private String memberPassword;
    @NotBlank(message = "이것도!!!!!")
    private String memberName;
}

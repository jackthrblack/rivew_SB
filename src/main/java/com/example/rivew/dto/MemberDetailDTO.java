package com.example.rivew.dto;

import com.example.rivew.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberDetailDTO {

    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static MemberDetailDTO toMemberDetailDTO(MemberEntity m) {
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(m.getId());
        memberDetailDTO.setMemberEmail(m.getMemberEmail());
        memberDetailDTO.setMemberPassword(m.getMemberPassword());
        memberDetailDTO.setMemberName(m.getMemberName());
        return memberDetailDTO;
    }
}

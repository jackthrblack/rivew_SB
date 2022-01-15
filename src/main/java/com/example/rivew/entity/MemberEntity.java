package com.example.rivew.entity;

import com.example.rivew.dto.MemberSaveDTO;
import com.example.rivew.dto.MemberUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="review")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="memberId")
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        return memberEntity;
    }

    public static MemberEntity toUpdateMember(MemberUpdateDTO memberUpdateDTO) {

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setId(memberUpdateDTO.getMemberId());
        memberEntity.setMemberEmail(memberUpdateDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberUpdateDTO.getMemberPassword());
        memberEntity.setMemberName(memberUpdateDTO.getMemberName());
        return memberEntity;
    }
}

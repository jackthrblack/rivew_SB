package com.example.rivew.service;

import com.example.rivew.dto.MemberDetailDTO;
import com.example.rivew.dto.MemberLoginDTO;
import com.example.rivew.dto.MemberSaveDTO;
import com.example.rivew.dto.MemberUpdateDTO;

import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO);

    boolean login(MemberLoginDTO memberLoginDTO);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findById(Long memberId);

    void deleteById(Long memberId);

    MemberDetailDTO findByEmail(String memberEmail);

    Long update(MemberUpdateDTO memberUpdateDTO);
}

package com.example.rivew.service;

import com.example.rivew.dto.MemberDetailDTO;
import com.example.rivew.dto.MemberLoginDTO;
import com.example.rivew.dto.MemberSaveDTO;
import com.example.rivew.entity.MemberEntity;
import com.example.rivew.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        MemberEntity emailCheckResult = mr.findByMemberEmail(memberSaveDTO.getMemberEmail());

        if (emailCheckResult != null) {
            throw new IllegalStateException("중복된 이메일입니ㅏㄷ!!");
        }
        return mr.save(memberEntity).getId();
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        if (memberEntity != null) {
            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public List<MemberDetailDTO> findAll() {

        List<MemberEntity> memberEntityList = mr.findAll();
        List<MemberDetailDTO> memberList = new ArrayList<>();
        for(MemberEntity m: memberEntityList){
            memberList.add(MemberDetailDTO.toMemberDetailDTO(m));
        }
        return memberList;
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {

        Optional<MemberEntity> memberEntityOptional = mr.findById(memberId);
        MemberEntity memberEntity = memberEntityOptional.get();
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);

        return memberDetailDTO;
    }

    @Override
    public void deleteById(Long memberId) {
        mr.deleteById(memberId);
    }

}

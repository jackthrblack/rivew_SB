package com.example.rivew.service;

import com.example.rivew.dto.ReviewDetailDTO;
import com.example.rivew.dto.ReviewSaveDTO;
import com.example.rivew.entity.MemberEntity;
import com.example.rivew.entity.ReviewEntity;
import com.example.rivew.repository.MemberRepository;
import com.example.rivew.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final MemberRepository mr;
    private final ReviewRepository rr;

    @Override
    public Long save(ReviewSaveDTO reviewSaveDTO) {

        MemberEntity memberEntity = mr.findByMemberEmail(reviewSaveDTO.getReviewWriter());
        ReviewEntity reviewEntity = ReviewEntity.toSave(reviewSaveDTO, memberEntity);

        return rr.save(reviewEntity).getReviewId();
    }

    @Override
    public List<ReviewDetailDTO> findAll(Long memberId) {

        MemberEntity memberEntity = mr.findById(memberId).get();
        List<ReviewEntity> reviewEntityList = memberEntity.getReviewEntityList();
        List<ReviewDetailDTO> reviewList = new ArrayList<>();
        for(ReviewEntity r : reviewEntityList){
            ReviewDetailDTO reviewDetailDTO = ReviewDetailDTO.toReviewDetailDTO(r);
            reviewList.add(reviewDetailDTO);
            System.out.println(reviewDetailDTO.getReviewStar());
        }
        return reviewList;
    }

    @Override
    public double avg(Long memberId) {

        double avg=0;
        int sum=0;

        MemberEntity memberEntity = mr.findById(memberId).get();
        List<ReviewEntity> reviewEntityList = memberEntity.getReviewEntityList();
        List<ReviewDetailDTO> reviewList = new ArrayList<>();
        for(ReviewEntity r: reviewEntityList){
            ReviewDetailDTO reviewDetailDTO = ReviewDetailDTO.toReviewDetailDTO(r);
                sum += r.getReviewStar();
                System.out.println("sum = " + sum);
        }
        avg = (double)sum / reviewEntityList.size() ;
        System.out.println(avg);
        System.out.println("sadf"+avg);
        avg= Math.round(avg *10)/10.0;
        System.out.println(avg);

        return avg;
    }
}

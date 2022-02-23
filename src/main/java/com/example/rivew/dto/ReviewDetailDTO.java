package com.example.rivew.dto;

import com.example.rivew.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDetailDTO {

    private Long reviewId;
    private Long memberId;
    private String reviewTitle;
    private String reviewContents;
    private String reviewWriter;
    private int reviewStar;


    public static ReviewDetailDTO toReviewDetailDTO(ReviewEntity r) {

        ReviewDetailDTO reviewDetailDTO = new ReviewDetailDTO();

        reviewDetailDTO.setReviewId(r.getReviewId());
        reviewDetailDTO.setReviewTitle(r.getReviewTitle());
        reviewDetailDTO.setReviewContents(r.getReviewContents());
        reviewDetailDTO.setReviewWriter(r.getReviewWriter());
        reviewDetailDTO.setReviewStar(r.getReviewStar());
        reviewDetailDTO.setMemberId(r.getMemberEntity().getId());
        return reviewDetailDTO;
    }
}

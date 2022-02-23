package com.example.rivew.entity;

import com.example.rivew.dto.ReviewSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="Review_table")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reviewId;

    @Column
    private String reviewTitle;

    @Column
    private String reviewContents;

    @Column
    private String reviewWriter;

    @Column
    private int reviewStar;

    @ManyToOne
    @JoinColumn(name="memberId")
    private MemberEntity memberEntity;

    public static ReviewEntity toSave(ReviewSaveDTO reviewSaveDTO, MemberEntity memberEntity) {

        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setReviewTitle(reviewSaveDTO.getReviewTitle());
        reviewEntity.setReviewWriter(reviewSaveDTO.getReviewWriter());
        reviewEntity.setReviewContents(reviewSaveDTO.getReviewContents());
        reviewEntity.setReviewStar(reviewSaveDTO.getReviewStar());
        reviewEntity.setMemberEntity(memberEntity);
        return reviewEntity;
    }
}

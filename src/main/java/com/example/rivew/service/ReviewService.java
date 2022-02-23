package com.example.rivew.service;

import com.example.rivew.dto.ReviewDetailDTO;
import com.example.rivew.dto.ReviewSaveDTO;

import java.util.List;

public interface ReviewService {
    Long save(ReviewSaveDTO reviewSaveDTO);

    List<ReviewDetailDTO> findAll(Long memberId);

    double avg(Long memberId);
}

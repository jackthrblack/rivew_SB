package com.example.rivew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSaveDTO {

    private Long memberId;
    private String reviewTitle;
    private String reviewContents;
    private String reviewWriter;
    private int reviewStar;
}

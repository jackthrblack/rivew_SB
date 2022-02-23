package com.example.rivew.controller;

import com.example.rivew.dto.ReviewDetailDTO;
import com.example.rivew.dto.ReviewSaveDTO;
import com.example.rivew.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService rs;

    @GetMapping("/save")
    public String save_form(){
        return "/review/save";
    }
    @PostMapping("/save")
    public String save(HttpSession session,Model model, @ModelAttribute ReviewSaveDTO reviewSaveDTO, @RequestParam("memberId") Long memberId){
        System.out.println("Asdfsdaf");
       rs.save(reviewSaveDTO);
       List<ReviewDetailDTO> reviewList = rs.findAll(reviewSaveDTO.getMemberId());
        model.addAttribute("review", reviewList);

        double reviewAvg = rs.avg(memberId);
        session.setAttribute("avg", reviewAvg);
        return "/review/test";
    }
}

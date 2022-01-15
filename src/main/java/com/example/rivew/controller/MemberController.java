package com.example.rivew.controller;

import com.example.rivew.dto.MemberDetailDTO;
import com.example.rivew.dto.MemberLoginDTO;
import com.example.rivew.dto.MemberSaveDTO;
import com.example.rivew.dto.MemberUpdateDTO;
import com.example.rivew.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.example.rivew.common.SessionConst.LOGIN_EMAIL;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService ms;

    @GetMapping("save")
    public String save_form(Model model){
        model.addAttribute("member", new MemberSaveDTO());
        return "member/save";
    }

    @PostMapping("save")
    public String save(@Validated @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "member/save";
        }

        try{
            Long memberId=ms.save(memberSaveDTO);
        } catch (IllegalStateException e){
            // e.getMessage()에는 서비스에서 지정한 예외메시지가 담겨있음
            bindingResult.reject("emailCheck", e.getMessage());
            return "member/save";
        }


        return "index";
    }

    @GetMapping("login")
    public String login_form(Model model){
        model.addAttribute("login", new MemberLoginDTO());
        return"member/login";
    }

    @PostMapping("login")
    public String login(@Validated @ModelAttribute("login") MemberLoginDTO memberLoginDTO, BindingResult bindingResult, HttpSession session){



        if(bindingResult.hasErrors()){
            return "member/login";
        }

        boolean loginResult = ms.login(memberLoginDTO);

        if(loginResult){
            session.setAttribute("loginEmail",memberLoginDTO.getMemberEmail());
            return "redirect:/member/";
        }else{
            bindingResult.reject("loginFail", "이메일 또는 비밀번호가 틀립니다!! 틀리다고!! 틀려!!!");
            return "member/login";
        }
    }

    @GetMapping
    public String findAll(Model model){
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList",memberList);
        return "member/findAll";
    }

  @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model) {
      MemberDetailDTO member = ms.findById(memberId);
      model.addAttribute("member", member);
      return "member/findById";
  }

    @PostMapping("{memberId}")
    public @ResponseBody MemberDetailDTO detail(@PathVariable("memberId") Long memberId){

        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        return memberDetailDTO;
    }

    @GetMapping("delete/{memberId}")
    public String delete(@PathVariable("memberId") Long memberId){
        ms.deleteById(memberId);
        return "redirect:/member/";
    }

    @DeleteMapping("{memberId}")
    public ResponseEntity delete2(@PathVariable("memberId") Long memberId){
        ms.deleteById(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("update")
    public String update_form(Model model, HttpSession session){

        String memberEmail = (String) session.getAttribute(LOGIN_EMAIL);
        MemberDetailDTO member = ms.findByEmail(memberEmail);
        model.addAttribute("member",member);
        return "member/update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute MemberUpdateDTO memberUpdateDTO){

        ms.update(memberUpdateDTO);
        return "redirect:/member/"+memberUpdateDTO.getMemberId();
    }

    @PutMapping("{memberId}")
    public ResponseEntity update2(@RequestBody MemberUpdateDTO memberUpdateDTO){

        Long memberId = ms.update(memberUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}

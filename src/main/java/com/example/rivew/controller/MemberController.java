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
    public String save(HttpSession session,@Validated @ModelAttribute("member") MemberSaveDTO memberSaveDTO, MemberDetailDTO memberDetailDTO, BindingResult bindingResult){

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


        return "index3";
    }

    @GetMapping("login")
    public String login_form(Model model){
        model.addAttribute("login", new MemberLoginDTO());
        return"member/login";
    }

    @PostMapping("login")
    public String login(@Validated @ModelAttribute("login") MemberLoginDTO memberLoginDTO, BindingResult bindingResult, HttpSession session){
        boolean loginResult = ms.login(memberLoginDTO);

       if(loginResult){
           session.setAttribute(LOGIN_EMAIL,memberLoginDTO.getMemberEmail());
           Long loginId = ms.findByMemberId(memberLoginDTO.getMemberEmail());
           session.setAttribute("loginId",loginId);
           System.out.println(loginId);
           return "index3";
       }else{
           return "/member/login";
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
        if(memberEmail != null){
            System.out.println("bbfdsbdsfb="+memberEmail);
            MemberDetailDTO member = ms.findByEmail(memberEmail);
            model.addAttribute("member",member);
        }else if(memberEmail == null){
            MemberDetailDTO member = ms.findById((Long) session.getAttribute("memberId"));
            model.addAttribute("member",member);

            System.out.println("asdfadsf!!!@!@!@!@");
        }
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

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index3";
    }
}

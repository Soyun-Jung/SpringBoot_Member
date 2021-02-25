package com.icia.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

import lombok.AllArgsConstructor;

// 세션 저장
@SessionAttributes("loginId")
@Controller
@AllArgsConstructor
public class MemberController {
   
   private MemberService ms;
   
   
   // 회원가입 폼 출력
   @GetMapping("/member/join")
   public String memberJoinForm() {
      return "memberjoin";
   }
   
   // 로그인 폼 출력
   @GetMapping("/member/login")
   public String memberloginForm() {
      return "memberlogin";
   }
   
   // 회원가입 처리 
   @PostMapping("/member/join")
   public String memberJoin(MemberDTO member) {
      String result = ms.memberJoin(member);
      if (result != null) {
         return "memberlogin";   
      }else {
         return "home";
      }
   }
   
   @PostMapping("/member/login")
   public String memberLogin(MemberDTO member, Model model) {
      MemberDTO loginMember = ms.memberLogin(member);
      if (loginMember != null) {
         model.addAttribute("loginMember",loginMember);
         model.addAttribute("loginId",loginMember.getMemail());
         return "mypage";
      }
      else {
         return "home";
      }
   }
   
   @GetMapping("/member/list")
   public String memberList(Model model) {
      List<MemberDTO> memberList = ms.memberList();
      model.addAttribute("memberList", memberList);
      return "memberlist";
   }
   
}


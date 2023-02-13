package com.glpsns.controller;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.glpsns.dto.ProfileFormDto;
import com.glpsns.entity.Member;
import com.glpsns.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProfileController {
	
	private final  MemberService memberService;

	/*
	 * @GetMapping(value = "/profile/{memberId}") public String
	 * profileMain(@PathVariable("memberId") Long memberId, Model model) { try {
	 * ProfileFormDto profileFormDto = memberService.getProfile(memberId);
	 * model.addAttribute(profileFormDto); } catch(EntityNotFoundException e) {
	 * model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
	 * model.addAttribute("profileFormDto", new ProfileFormDto()); } return
	 * "profile/profileForm"; }
	 */
	
	//프로필 페이지
		@GetMapping(value="/profile/my")
		public String profileMain(Model model, Principal principal) {
			String email = principal.getName();
			ProfileFormDto profileFormDto = memberService.getProfile(email);
			model.addAttribute("profileFormDto", profileFormDto);
			model.addAttribute("member", profileFormDto);
			return "profile/profileForm";
		}
		
		
	
}

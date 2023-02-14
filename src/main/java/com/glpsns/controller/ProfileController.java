package com.glpsns.controller;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.glpsns.dto.ProfileFormDto;
import com.glpsns.dto.StoryFormDto;
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
			model.addAttribute("storys", profileFormDto);
			return "profile/profileForm";
		}
		
		//프로필 수정 페이지 불러오기
		@GetMapping(value = "/profile/update")
		public String profileUpdate(Model model, Principal principal) {
			String email = principal.getName();
			ProfileFormDto profileFormDto = memberService.getProfile(email);
			model.addAttribute("profileFormDto", profileFormDto);
			return "profile/profileUpdateForm";
		}
		
		//프로필 수정
		@PostMapping(value = "/profile/update")
		public String itemUpdate(@Valid ProfileFormDto profileFormDto, Model model) {
			try {
				memberService.updateItem(profileFormDto);
			} catch (Exception e) {
				model.addAttribute("errorMessage","상품 수정 중 에러가 발생하였습니다.");
				return "profile/profileUpdateForm";				
			}
			return "profile/profileForm";
		}
	
}

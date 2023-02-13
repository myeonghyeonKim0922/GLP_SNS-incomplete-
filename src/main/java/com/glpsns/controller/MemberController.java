package com.glpsns.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.glpsns.dto.MemberFormDto;
import com.glpsns.service.MemberService;
import com.glpsns.entity.Member;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	// 회원가입 화면
	@GetMapping(value = "/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}

	@PostMapping(value = "/new")
	public String MainForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		// @Valid : 유효성을 검증하려는 객체 앞에 붙인다.
		// bindingResult: 유효성 검증후에 결과를 넣어준다.

		// 에러가 있다면 회원가입 페이지로 이동
		if (bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		try {
			Member member = Member.createmember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}

		return "redirect:/";
	}

	// 로그인 화면
	@GetMapping(value = "/login")
	public String loginMember() {
		return "member/memberLoginForm";
	}

	// 로그인을 실패했을때
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/memberLoginForm";
	}

}

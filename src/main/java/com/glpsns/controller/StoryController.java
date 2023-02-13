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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.glpsns.dto.MainStoryDto;
import com.glpsns.dto.StoryFormDto;
import com.glpsns.service.MediaService;
import com.glpsns.service.StoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StoryController {

	private final StoryService storyService;
	
	private final MediaService mediaService;
	
	//스토리 쓰기 페이지 보여주기
	@GetMapping(value= "/story/create")
	public String storyForm(Model model) {
		model.addAttribute("storyFormDto", new StoryFormDto());
		return "story/storyForm";
	}
	
	//스토리 상세 페이지 보여주기
	@GetMapping(value= "/story/dtl/{storyId}")
	public String storyDtlForm(@PathVariable("storyId") Long storyId,Model model) {
		try {
			MainStoryDto mainStoryDto = storyService.getStoryDtl(storyId);
			model.addAttribute("storys",mainStoryDto);
		}catch(EntityNotFoundException e) {
			model.addAttribute("errorMessage","존재하지 않는 스토리입니다.");
			model.addAttribute("storyFormDto", new StoryFormDto());
		}
		return "story/storyDtl";
	} 
	

	//스토리 쓰기
	@PostMapping(value = "/story/create")
	public String MainForm(@Valid StoryFormDto storyFormDto, BindingResult bindingResult, Model model,
			@RequestParam("itemImgFile") MultipartFile itemImgFile, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "story/storyForm";
		}
		
		String email = principal.getName();

		try {
			storyService.saveStory(storyFormDto, itemImgFile, email);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "스토리 등록 중 에러가 발생했습니다.");
			e.printStackTrace();
			return "story/storyForm";
		}
		return "redirect:/";
	}
	
	//상품 수정 페이지 보기
		@GetMapping(value="/story/create/{storyId}")
		public String storyUpdate(@PathVariable("storyId") Long storyId,Model model) {
			try {
				StoryFormDto storyFormDto = storyService.getStoryUpdate(storyId);
				model.addAttribute(storyFormDto);
			}catch(EntityNotFoundException e) {
				model.addAttribute("errorMessage","존재하지 않는 스토리입니다.");
				model.addAttribute("storyFormDto", new StoryFormDto());
			}
			return "story/storyForm";
		}
		
		//상품 수정
		@PostMapping(value = "/story/create/{storyId}")
		public String itemUpdate(@Valid StoryFormDto storyFormDto, BindingResult bindingResult, 
				Model model, @RequestParam("itemImgFile") MultipartFile itemImgFile) {
			if(itemImgFile.isEmpty() && storyFormDto.getId() == null) {
				model.addAttribute("errorMessage","이미지 또는 유튜브 링크 필수 입력 값 입니다.");
				return "story/storyForm";
			}
			try {
				storyService.updateStory(storyFormDto, itemImgFile);
			} catch (Exception e) {
				model.addAttribute("errorMessage","상품 수정 중 에러가 발생하였습니다.");
				return "story/storyForm";				
			}
			return "redirect:/";
		}
		
		@GetMapping(value = "/story/delete")
		public String storydelete(Long storyId) throws Exception {
			storyService.storydelete(storyId);
			return "redirect:/";
		}
		

}
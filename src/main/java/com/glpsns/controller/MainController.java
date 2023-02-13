package com.glpsns.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.glpsns.dto.MemberFormDto;
import com.glpsns.dto.StorySearchDto;
import com.glpsns.service.StoryService;
import com.glpsns.dto.MainStoryDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final StoryService storyService;

	@GetMapping(value = "/")
	public String main(Model model) {
		
		List<MainStoryDto> mainStoryDtoList = storyService.getMainStory();
		model.addAttribute("storys", mainStoryDtoList);

		return "main";
	}
	
	
}

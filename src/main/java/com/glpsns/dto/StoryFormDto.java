package com.glpsns.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.glpsns.entity.Member;
import com.glpsns.entity.Story;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryFormDto {
	private Long id;
	
	@NotBlank(message = "내용은 필수 입력 값입니다.")
	private String content; //스토리 내용
	
	@NotNull(message = "게임 태그는 필수 입력 값입니다.")
	private String gameTag;
	
	private String youtubeLink;
	
	private MediaDto mediaDto;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Story createStory() {
		return modelMapper.map(this, Story.class);
	}
	
	public static StoryFormDto of(Story story) {
		return modelMapper.map(story, StoryFormDto.class);
	}
	
}

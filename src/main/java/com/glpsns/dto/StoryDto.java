package com.glpsns.dto;

import java.time.LocalDateTime;

import com.glpsns.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryDto {
	
	private Long id;
	
	private String content;
	
	private String gameTag;
	
	private LocalDateTime writingDate;
	
	private Member member;
}

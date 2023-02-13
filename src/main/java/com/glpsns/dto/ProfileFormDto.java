package com.glpsns.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.glpsns.dto.ProfileFormDto;
import com.glpsns.entity.Member;
import com.glpsns.entity.Story;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileFormDto {
	
	private Long id; //맴버코드

	private String introWriting;
	
	private String nickname;
	
	private String profileImg;
	
	private Story story;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ProfileFormDto of(Member member) {
		return modelMapper.map(member, ProfileFormDto.class);
	}
	
}

package com.glpsns.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.glpsns.dto.ProfileFormDto;
import com.glpsns.entity.Media;
import com.glpsns.entity.Member;
import com.glpsns.entity.Story;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileFormDto {
	public ProfileFormDto(Member member) {
		this.id = member.getId();
		this.introWriting = member.getIntroWriting();
		this.nickname = member.getNickname();
		this.email = member.getEmail();
		this.profileImg = member.getProfileImg();
	}
	private Long id; //맴버코드

	private String introWriting;
	
	private String nickname;
	
	private String email;
	
	private String profileImg;
	
	private List<Story> story;
	
	private List<Media> media;
	
	public void addMediaDto(List<Media> media){
    	this.media = media;
    }
	
	public void addStoryDto(List<Story> story) {
		this.story = story;
	}
}

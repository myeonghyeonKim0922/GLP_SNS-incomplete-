package com.glpsns.dto;

import com.glpsns.entity.Media;
import com.glpsns.entity.Member;
import com.glpsns.entity.Story;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainStoryDto {
	public MainStoryDto(Story story) {
		this.id = story.getId();
		this.content = story.getContent();
		this.gameTag = story.getGameTag();
		this.youtubeLink = story.getYoutubeLink();
		this.member = story.getMember();
	}

	private Long id;

	private String gameTag;

	private String content;

	private String youtubeLink; // 유튜브 링크

	private Member member;

	private Media media;
	
    //이미지 리스트
    public void addMediaDto(Media media){
    	this.media = media;
    }

}

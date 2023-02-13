package com.glpsns.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.glpsns.dto.StoryFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="story")
@Getter
@Setter
@ToString
public class Story {
	
	@Id
	@Column(name="story_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@Column(name = "game_tag", nullable = false)
	private String gameTag;
	
	/*
	 * @CreatedDate //엔티티가 생성되서 저장될때 시간을 자동으로 저장
	 * 
	 * @Column(updatable = false, name = "writing_date") private LocalDateTime
	 * writingDate; //등록날짜
	 */	
	
	@Lob
	@Column(nullable = false)
	private String content;
	
	@Column(name = "youtube_link")
	private String youtubeLink; //유튜브 링크
	
	public void updateStory(StoryFormDto storyFormDto) {
		this.content =storyFormDto.getContent();
		this.youtubeLink = storyFormDto.getYoutubeLink();
		this.gameTag = storyFormDto.getGameTag();
	}
	
	
	public static Story createstory (StoryFormDto storyFormDto, Member member) {
		Story story = new Story();
		story.setContent(storyFormDto.getContent());
		story.setGameTag(storyFormDto.getGameTag());
		story.setYoutubeLink(storyFormDto.getYoutubeLink());
		story.setMember(member);
		return story;
	}
}

package com.glpsns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "media")
@Getter
@Setter
@ToString
public class Media {

	@Id
	@Column(name = "media_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "img_url")
	private String imgUrl; //이미지 조회 경로
	
	@Column(name = "img_name")
	private String imgName; //이미지 파일명
	
	private String oriImgName; //원본 이미지 파일명
	
	@JoinColumn(name = "story_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Story story; //게시글 아이디
	
	/*
	 * @OneToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "member_id") private Member member;
	 */
	
	public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName = oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
}

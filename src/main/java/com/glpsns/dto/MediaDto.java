package com.glpsns.dto;

import org.modelmapper.ModelMapper;
import com.glpsns.entity.Media;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaDto {

	private Long id;
	
	private String imgUrl; //이미지 조회 경로
	
	private String imgName; //이미지 파일명
	
	private String oriImgName; //원본 이미지 파일명
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static MediaDto of(Media media) {
		return modelMapper.map(media, MediaDto.class);
	}
}

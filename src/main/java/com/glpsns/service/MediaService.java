package com.glpsns.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.glpsns.entity.Media;
import com.glpsns.repository.MediaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MediaService {
	
	@Value("${mediaLocation}")
	private String mediaLocation;
	
	private final MediaRepository mediaRepository;
	
	private final FileService fileService;
	
	//이미지 저장 메소드
	public void saveStoryImg(Media media, MultipartFile itemImgFile) throws Exception {
		String oriImgName = itemImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl ="";
		
		//파일 업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(mediaLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/story/" + imgName;
		}
		
		//상품 이미지 정보 저장
		media.updateItemImg(oriImgName, imgName, imgUrl);
		mediaRepository.save(media);
	}
	
	//이미지 업데이트 메소드
	public void updateItemImg(Long storyId, MultipartFile itemImgFile) throws Exception {
		if(!itemImgFile.isEmpty()) { //파일이 있으면
			Media savedItemImg = mediaRepository.findByStoryId(storyId);
			
			//기존 이미지 파일 삭제
			if(!StringUtils.isEmpty(savedItemImg.getImgUrl())) {
				fileService.deleteFile(mediaLocation + "/" + savedItemImg.getImgName());
			}
			
			//수정된 이미지 파일 업로드
			String oriImgName = itemImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile(mediaLocation, oriImgName, itemImgFile.getBytes());
			String imgUrl = "/images/story/" + imgName;
			
			//★ savedItemImg는 현재 영속상태이므로 데이터를 변경하는 것만으로 변경감지 기능이 동작하여 트랜잭션이 끝날때 update쿼리가 실행됨
			// -> 엔티티가 반드시 영속상태여야 한다.
			savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
		}
		
	}
	
	//스토리 아이디로 미디어 아이디 찾기
	public Media findMediaId(Long storyId) throws Exception {
		Media media = mediaRepository.findByStoryId(storyId);
		return media;
	}

}

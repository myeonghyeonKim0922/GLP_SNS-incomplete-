package com.glpsns.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.glpsns.dto.MainStoryDto;
import com.glpsns.dto.MediaDto;
import com.glpsns.dto.StoryFormDto;
import com.glpsns.dto.StorySearchDto;
import com.glpsns.entity.Media;
import com.glpsns.entity.Member;
import com.glpsns.entity.Story;
import com.glpsns.repository.MediaRepository;
import com.glpsns.repository.MemberRepository;
import com.glpsns.repository.StoryRepository;
import com.glpsns.repository.StoryRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service // service 클래스의 역할
@Transactional // 서비스 클래서에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다.
@RequiredArgsConstructor
@Component
public class StoryService {
	private final StoryRepository storyRepository;
	private final MediaRepository mediaRepository;
	private final MemberRepository memberRepository;
	private final MediaService mediaService;
	private final StoryRepositoryCustom storyRepositoryCustom;

	// 스토리 저장하기
	public Long saveStory(StoryFormDto storyFormDto, MultipartFile itemImgFile, String email) throws Exception {
		Member member = memberRepository.findByEmail(email);

		Story story = Story.createstory(storyFormDto, member);
		storyRepository.save(story);

		Media media = new Media();
		media.setStory(story); // 조인

		mediaService.saveStoryImg(media, itemImgFile);
		return story.getId();
	}

	// 스토리 리스트 가져오기
	@Transactional(readOnly = true)
	public List<MainStoryDto> getMainStory() {
		List<Story> storyList = storyRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		List<MainStoryDto> mainStoryDtoList = new ArrayList<>();

		
		for (Story story : storyList) {
			MainStoryDto mainStoryDto = new MainStoryDto(story);
			Media media = mediaRepository.findByStoryId(story.getId());
			mainStoryDto.addMediaDto(media);
			mainStoryDtoList.add(mainStoryDto);
		}

		return mainStoryDtoList;
	}
	
	//스토리 상세 페이지 불러오기
	@Transactional(readOnly = true)
	public MainStoryDto getStoryDtl(Long storyId) {
			Story story = storyRepository.findById(storyId).orElseThrow(EntityNotFoundException::new);
			MainStoryDto mainStoryDto = new MainStoryDto(story);
			Media media = mediaRepository.findByStoryId(storyId);
			mainStoryDto.addMediaDto(media);
			return mainStoryDto;
	}
	
	//스토리 수정 페이지 불러오기
	@Transactional(readOnly = true) // 트랜잭션 읽기 전용(변경감지 수행하지 않음) -> 성능향상
	public StoryFormDto getStoryUpdate(Long storyId) {
		// item_img 테이블의 이미지를 가져온다.
		Media storyImg = mediaRepository.findByStoryId(storyId);

		// 엔티티 객체 -> Dto객체로 변환
		MediaDto mediaDto = MediaDto.of(storyImg);

		// 2.item테이블에 있는 데이터를 가져온다.
		Story story = storyRepository.findById(storyId).orElseThrow(EntityNotFoundException::new);

		// 엔티티 객체 -> Dto객체로 변환
		StoryFormDto storyFormDto = StoryFormDto.of(story);

		// 상품의 이미지 정보를 넣어준다.
		storyFormDto.setMediaDto(mediaDto);

		return storyFormDto;
	}

	// 스토리 수정
	public Long updateStory(StoryFormDto storyFormDto, MultipartFile itemImgFile) throws Exception {
		Story story = storyRepository.findById(storyFormDto.getId()).orElseThrow(EntityNotFoundException::new);

		story.updateStory(storyFormDto);

		Long storyIds = storyFormDto.getId(); // 상품 이미지 아이디 리스트를 조회

		mediaService.updateItemImg(storyIds, itemImgFile);

		return story.getId();
	}

	
	//스토리 삭제
	public void storydelete(Long storyid) throws Exception {
		Media media = mediaService.findMediaId(storyid);
		Long mediaid = media.getId();
		mediaRepository.deleteById(mediaid);
		storyRepository.deleteById(storyid);
	}
}

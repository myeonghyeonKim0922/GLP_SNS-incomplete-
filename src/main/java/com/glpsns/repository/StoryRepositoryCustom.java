package com.glpsns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glpsns.dto.MainStoryDto;
import com.glpsns.entity.Story;
import com.glpsns.dto.StorySearchDto;

public interface StoryRepositoryCustom extends JpaRepository<Story, Long>{
	
	/* MainStoryDto getMainStoryPage(StorySearchDto storySearchDto); */
}

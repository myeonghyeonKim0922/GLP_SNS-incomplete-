package com.glpsns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glpsns.entity.Media;

public interface MediaRepository extends JpaRepository<Media, Long>{
//	List<Media> findByMemberIdOrderByIdAsc(Long memberId);
	Media findByStoryId(Long storyId);
}

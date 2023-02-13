package com.glpsns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.glpsns.entity.Story;

public interface StoryRepository extends JpaRepository<Story, Long>, QuerydslPredicateExecutor<Story>, StoryRepositoryCustom {
	
}

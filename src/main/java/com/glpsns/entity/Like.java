package com.glpsns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Entity
//@Table(name = "like")
//@Getter
//@Setter
//@ToString
public class Like {

//	@Id
//	@Column(name = "like_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	@JoinColumn(name = "story_id")
//	@OneToOne(fetch = FetchType.LAZY)
//	private Story story;
//	
//	@JoinColumn(name = "member_id")
//	@OneToOne(fetch = FetchType.LAZY)
//	private Member member;
}

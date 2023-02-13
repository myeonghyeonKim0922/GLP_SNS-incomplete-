package com.glpsns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Entity
//@Table(name = "follow")
//@Getter
//@Setter
//@ToString
public class Follow {

//	@Id
//	@Column(name = "follow_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	@Column(name = "follower_id")
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Member follower;
//	
//	@Column(name = "following_id")
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Member following;
}

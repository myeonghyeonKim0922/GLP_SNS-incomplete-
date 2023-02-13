package com.glpsns.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Entity
//@Table(name = "reply")
//@Getter
//@Setter
//@ToString
public class Reply {
	
//	@Id
//	@Column(name = "reply_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	
//	private String content;
//	
//	@Column(name = "reply_date")
//	private LocalDateTime replyDate;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "comment_id")
//	private Comment comment;
}

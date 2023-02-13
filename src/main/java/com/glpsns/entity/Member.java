package com.glpsns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.glpsns.dto.MemberFormDto;
import com.glpsns.dto.ProfileFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {
	
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String nickname;
	
	private String introWriting;
	
	private String profileImg;
	
	public static Member createmember (MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setEmail(memberFormDto.getEmail());
		member.setNickname(memberFormDto.getNickname());
		
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		member.setPassword(password);
		return member;
	}
	
	public void updateProfile(ProfileFormDto profileFormDto) {
		this.introWriting = profileFormDto.getIntroWriting();
		this.profileImg=profileFormDto.getProfileImg();
	}
}

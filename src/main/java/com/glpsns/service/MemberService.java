package com.glpsns.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.glpsns.repository.MemberRepository;
import com.glpsns.dto.ProfileFormDto;
import com.glpsns.entity.Member;
import lombok.RequiredArgsConstructor;

@Service //service 클래스의 역할
@Transactional //서비스 클래서에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다. 
@RequiredArgsConstructor
public class MemberService implements UserDetailsService { //UserDetailsService: 로그인시 request에서 넘어온 사용자 정보를 받음
	private final MemberRepository memberRepository; //의존성 주입
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email); 
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		//userDetails의 객체를 반환
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles("USER")
				.build();
	}
	
	
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member); //member 테이블에 insert
	}
	
	//이메일 중복체크 메소드
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
	
	@Transactional(readOnly = true) //트랜잭션 읽기 전용(변경감지 수행하지 않음) -> 성능향상
	public ProfileFormDto getProfile(String email) { 
		//2.item테이블에 있는 데이터를 가져온다.
		Member member = memberRepository.findByEmail(email);
		
		
		//엔티티 객체 -> Dto객체로 변환
		ProfileFormDto profileFormDto = ProfileFormDto.of(member);
		
		//상품의 이미지 정보를 넣어준다.
		
		return profileFormDto;
	}
	
	public Long updateItem(ProfileFormDto profileFormDto, List<MultipartFile> itemImgFileList) throws Exception {
		Member member = memberRepository.findById(profileFormDto.getId())
				.orElseThrow(EntityNotFoundException::new);
		
		member.updateProfile(profileFormDto);
		
		return member.getId();
	}

}
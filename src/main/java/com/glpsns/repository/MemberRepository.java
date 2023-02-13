package com.glpsns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.glpsns.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email); //회원가입시 중복이 있는지 검사
}

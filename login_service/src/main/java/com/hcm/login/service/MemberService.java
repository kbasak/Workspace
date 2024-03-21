package com.hcm.login.service;

import java.util.List;
import java.util.Optional;

import com.hcm.login.entity.Member;

public interface MemberService {
	public List<Member> getMember();

	public Optional<Member> getMember(long memberid);

	public Member addMember(Member member);

	boolean countByUserName(String username);

}

package com.hcm.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.login.dao.MemberDao;
import com.hcm.login.entity.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public List<Member> getMember() {
		return memberDao.findAll();
	}

	@Override
	public Member addMember(Member member) {
		memberDao.save(member);
		return member;
	}

	@Override
	public Optional<Member> getMember(long memberid) {
		return memberDao.findById(memberid);
	}

	@Override
	public boolean countByUserName(String username) {
		return memberDao.existsByUsername(username);
	}

}

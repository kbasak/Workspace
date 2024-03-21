package com.hcm.login.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcm.login.entity.Member;

public interface MemberDao extends JpaRepository<Member, Long> {

	Boolean existsByUsername(String username);

	List<Member> findByFnameAndLname(String fname, String lname);

	List<Member> findByPhysicianid(int physicianid);
	
	List<Member> findByUsername(String userName);

	List<Member> findByMemberid(long memberid);
}

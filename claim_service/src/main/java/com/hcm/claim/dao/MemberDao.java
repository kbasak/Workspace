package com.hcm.claim.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcm.claim.entity.Member;

public interface MemberDao extends JpaRepository<Member, Long> {

	List<Member> findByFnameAndLname(String fname, String lname);

	List<Member> findByPhysicianid(int physicianid);

	List<Member> findByUsername(String userName);
}

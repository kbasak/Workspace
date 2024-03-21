package com.hcm.claim.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcm.claim.entity.SearchInfo;

public interface SearchDao extends JpaRepository<SearchInfo, Long> {

	List<SearchInfo> findByFnameAndLname(String fname, String lname);

	List<SearchInfo> findByPhysicianid(long physicianid);
	
	List<SearchInfo> findByClaimid(long claimid);
	
	List<SearchInfo> findByMemberid(long memberid);
}

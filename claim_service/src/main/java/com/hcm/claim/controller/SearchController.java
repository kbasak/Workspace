package com.hcm.claim.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.claim.dao.MemberDao;
import com.hcm.claim.dao.PhysicianDao;
import com.hcm.claim.dao.SearchDao;
import com.hcm.claim.entity.ClaimInfo;
import com.hcm.claim.entity.Member;
import com.hcm.claim.entity.SearchInfo;
import com.hcm.claim.service.ClaimService;

@RestController
@CrossOrigin(origins = "*")
public class SearchController {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PhysicianDao physicianDao;
	
	@Autowired
	private SearchDao searchDao;

	@Autowired
	private ClaimService claimService;
	
	Logger logger=LoggerFactory.getLogger(SearchController.class);

	@GetMapping("/physicianid/{physicianid}")
	public List<SearchInfo> getMemberByPhysician(@PathVariable long physicianid) {
		return this.searchDao.findByPhysicianid(physicianid);
	}

	@GetMapping("/physicianname/{physicianname}")
	public List<SearchInfo> getMemberByPname(@PathVariable String physicianname) {
		int pid = (int) (this.physicianDao.findByPhysicianname(physicianname).get(0).getPhysicianid());
		return this.searchDao.findByPhysicianid(pid);
	}

	// Search member by name
	@GetMapping("/name/{fname}-{lname}")
	public List<SearchInfo> getMemberByName(@PathVariable String fname, @PathVariable String lname) {
		return searchDao.findByFnameAndLname(fname, lname);
	}

	// search by member id
	@GetMapping("/memberid/{memberid}")
	public List<SearchInfo> getMember(@PathVariable long memberid) {
		return this.searchDao.findByMemberid(memberid);
	}

	// find by claim id
	@GetMapping("/claimid/{claimid}")
	public List<SearchInfo> searchByClaimId(@PathVariable long claimid) {
		return this.searchDao.findByClaimid(claimid);
	}

	@GetMapping("/member/uname/{userName}")
	public Long getByUsername(@PathVariable String userName) {
		return this.memberDao.findByUsername(userName).get(0).getMemberid();
		//return memberFeing.getByUsername(userName);
	}
	
	@GetMapping("/fetchclaims")
	public List<ClaimInfo> fetchClaims(){
		return this.claimService.fetchClaims();
	}
	
	@GetMapping("/searchclaims")
	public List<SearchInfo> searchClaims(){
		return this.claimService.searchClaims();
	}
	
	@GetMapping("/searchInfo/{id}")
	public Optional<SearchInfo> searchInfo(@PathVariable Long id){
		return this.searchDao.findById(id);
	}
	
	@GetMapping("/searchMem")
	public List<Member> fetchMem(){
		return this.memberDao.findAll();
	}
}

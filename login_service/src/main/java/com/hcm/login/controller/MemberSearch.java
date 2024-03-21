package com.hcm.login.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.login.dao.MemberDao;
import com.hcm.login.dao.PhysicianDao;
import com.hcm.login.entity.Member;

@RestController
@CrossOrigin(origins = "*")
public class MemberSearch {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private PhysicianDao physicianDao;

	Logger logger = LoggerFactory.getLogger(MemberSearch.class);

	@GetMapping("/physicianid/{physicianid}")
	public List<Member> getMemberByPhysician(@PathVariable int physicianid) {
		return this.memberDao.findByPhysicianid(physicianid);
	}

	@GetMapping("/physicianname/{physicianname}")
	public List<Member> getMemberByPname(@PathVariable String physicianname) {
		int pid = (int) (this.physicianDao.findByPhysicianname(physicianname).get(0).getPhysicianid());
		return this.memberDao.findByPhysicianid(pid);
	}

	// Search member by name
	@GetMapping("/name/{fname}-{lname}")
	public List<Member> getMemberByName(@PathVariable String fname, @PathVariable String lname) {
		return memberDao.findByFnameAndLname(fname, lname);
	}

	// search by member id
	@GetMapping("/memberid/{memberid}")
	public List<Member> getMember(@PathVariable long memberid) {
		return this.memberDao.findByMemberid(memberid);
	}

	@GetMapping("/searchMem")
	public List<Member> fetchMem() {
		return this.memberDao.findAll();
	}

	@GetMapping("/member/uname/{userName}")
	public Long getByUsername(@PathVariable String userName) {
		return this.memberDao.findByUsername(userName).get(0).getMemberid();

	}
}

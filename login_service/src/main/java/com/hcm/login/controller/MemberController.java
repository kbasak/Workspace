package com.hcm.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.login.entity.Member;
import com.hcm.login.service.MemberService;
import com.hcm.login.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(MemberController.class);

	// add member
	@PostMapping(path = "/addmember", consumes = "application/json")
	public Member addMember(@RequestBody Member member) {
//		Users users = new Users();
//		users.setUsername(member.getUsername());
//		users.setPassword(member.getPassword());
//		users.setUserrole(userDTO.getUserrole());
		if (this.userService.existByUserName(member.getUsername())) {
			logger.error("Username alredy exist");
			logger.info("Use different username");
			return null;
		} else if (member.getUsername() == "" || member.getFname() == "" || member.getLname() == ""
				|| member.getEmail() == "") {
			return null;
		} else {
			this.memberService.addMember(member);
			// this.userService.addUsers(users);
			logger.info("Member added sucessfully");
			return member;
		}
	}

}

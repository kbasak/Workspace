package com.demo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
//	@Autowired
//	UserDetailsService userDetailsService;
	
	@GetMapping("/getMsg")
	public String getMsg() {
//		String username=userDetailsService.loadUserByUsername("kausik").getUsername();
//		String password=userDetailsService.loadUserByUsername("kausik").getPassword();
//		String cred=username+" "+password;
		return "Spring Security Works";
	}
	@GetMapping("/bye")
	public String bye() {
		return "Get Lost!!!";
	}
}

package com.hcm.login.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.login.entity.Users;
import com.hcm.login.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class RegisterController {
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@PostMapping(path = "/register")
	public Users registerUser(@RequestBody Users user) {
		boolean b = this.userService.existByUserName(user.getUsername());
		if (b) {
			logger.info("UserName Already Exist");
			return null;
		}
//		else if(user.getUsername()=="" || user.getPassword()==""){
//			return null;
//		}	
		else {
			String pwd = passwordEncoder.encode(user.getPassword());
			user.setPassword(pwd);
			this.userService.addUsers(user);
			logger.info("User Added successfully");
			return user;
		}
	}

	@GetMapping(path = "/users")
	public List<Users> fetchUsers() {
		return this.userService.getUsers();
	}

}

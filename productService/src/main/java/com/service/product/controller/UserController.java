package com.service.product.controller;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.service.product.model.Role;
import com.service.product.model.User;
import com.service.product.repo.UserRepo;
import com.service.product.security.SecurityService;

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/showReg")
	public String showRegPage() {
		return "registerUser";
	}

	@PostMapping("/registerUser")
	public String register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		HashSet<Role> roles=new HashSet<>();
		Role role=new Role();
		role.setId(2l);
		roles.add(role);
		user.setRoles(roles);
		userRepo.save(user);
		return "login";
	}

	@GetMapping("/")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(String email, String password, HttpServletRequest request, HttpServletResponse response) {
		boolean loginResponse = securityService.login(email, password, request, response);
		if (loginResponse) {
			return "index";
		}
		return "login";

	}
}

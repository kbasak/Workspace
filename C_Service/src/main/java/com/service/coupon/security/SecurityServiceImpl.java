package com.service.coupon.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authentication;
	
	@Autowired
	SecurityContextRepository securityContextRepo;

	@Override
	public boolean login(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
		authentication.authenticate(token);
		boolean result = token.isAuthenticated();
		if (result) {
			SecurityContext context = SecurityContextHolder.getContext();
			context.setAuthentication(token);
			securityContextRepo.saveContext(context, request, response);
		}
		return result;
	}

}

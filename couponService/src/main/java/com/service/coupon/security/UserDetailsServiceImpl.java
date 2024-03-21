package com.service.coupon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.service.coupon.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.service.coupon.model.User user = userRepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found for email: "+username);
		}
		return new User(user.getEmail(), user.getPassword(), user.getRoles());
	}

}

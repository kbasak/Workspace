package com.login.patient.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.login.patient.dao.LoginDao;
import com.login.patient.entity.Login;

@Service
public class LoginServiceImpl implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Member is Coming..." + username);
		logger.info("BEGIN - [loadUserByUsername()]");
		logger.debug("Member Id : " + username);
		Login login;

		if (loginDao.findById(username).get() != null) {
			login = loginDao.findById(username).get();
			UserDetails user = new User(login.getUsername(), login.getPassword(), new ArrayList<>());

			logger.debug("User : " + user);
			logger.info("END - [loadUserByUsername()]");
			return user;
		}

		throw new UsernameNotFoundException("User not found!!");
	}
}

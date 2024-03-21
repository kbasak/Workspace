package com.hcm.login.service;

import java.util.List;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Service;

import com.hcm.login.dao.UserDao;
import com.hcm.login.entity.Users;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public List<Users> getUsers() {
		return userDao.findAll();
	}

	@Override
	public Users addUsers(Users users) {
		userDao.save(users);
		return users;
	}

	@Override
	public Boolean existByUserName(String username) {
		return userDao.existsById(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Member is Coming..." + username);
		logger.info("BEGIN - [loadUserByUsername()]");
		logger.debug("Member Id : " + username);
		Users users;

		if (userDao.findById(username).get() != null) {
			users = userDao.findById(username).get();
			UserDetails user = new User(users.getUsername(), users.getPassword(), new ArrayList<>());

			logger.debug("User : " + user);
			logger.info("END - [loadUserByUsername()]");
			return user;
		}

		throw new UsernameNotFoundException("User not found!!");
	}

}

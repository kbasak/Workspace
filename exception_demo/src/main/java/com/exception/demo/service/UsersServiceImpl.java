package com.exception.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.demo.dao.UserDao;
import com.exception.demo.entity.Users;
import com.exception.demo.exceptionHandler.UserNotFoundException;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserDao userDao;

	@Override
	public Users saveUsers(Users user) {
		return userDao.save(user);
	}

	@Override
	public List<Users> findUsers() {
		return userDao.findAll();
	}

	@Override
	public Optional<Users> findUserById(int userId) throws UserNotFoundException {
		Optional<Users> user = userDao.findById(userId);
		if (!user.equals(Optional.empty())) {
			return user;
		} else {
			throw new UserNotFoundException("User Not Found with User Id: "+userId);
		}
	}

	@Override
	public void deleteUser(int userId) throws UserNotFoundException {
		Optional<Users> user = userDao.findById(userId);
		if (!user.equals(Optional.empty())) {
			userDao.deleteById(userId);
		} else {
			throw new UserNotFoundException("User Not Found with User Id: "+userId);
		}
	}
}

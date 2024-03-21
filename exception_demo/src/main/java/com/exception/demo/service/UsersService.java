package com.exception.demo.service;

import java.util.List;
import java.util.Optional;

import com.exception.demo.entity.Users;
import com.exception.demo.exceptionHandler.UserNotFoundException;

public interface UsersService {
	public Users saveUsers(Users users);
	public List<Users> findUsers();
	public Optional<Users> findUserById(int id) throws UserNotFoundException;

	//delete user
	public void deleteUser(int id) throws UserNotFoundException;
}

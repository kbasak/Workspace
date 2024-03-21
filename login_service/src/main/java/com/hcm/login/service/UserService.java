package com.hcm.login.service;

import java.util.List;

import com.hcm.login.entity.Users;

public interface UserService {
	public List<Users> getUsers();

	public Users addUsers(Users users);

	Boolean existByUserName(String username);

}

package com.exception.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exception.demo.entity.Users;

public interface UserDao extends JpaRepository<Users, Integer>{

	//To find user by user id
	Optional<Users> findByUserId(int userId);

}

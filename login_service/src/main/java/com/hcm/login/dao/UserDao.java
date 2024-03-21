package com.hcm.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcm.login.entity.Users;

public interface UserDao extends JpaRepository<Users, String> {

}

package com.service.coupon.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.coupon.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByEmail(String email);
}

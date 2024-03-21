package com.service.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.product.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByEmail(String email);
}

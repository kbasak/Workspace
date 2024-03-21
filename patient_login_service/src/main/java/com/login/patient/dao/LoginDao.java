package com.login.patient.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.patient.entity.Login;

public interface LoginDao extends JpaRepository<Login, String> {

}

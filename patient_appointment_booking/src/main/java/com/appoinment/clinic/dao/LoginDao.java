package com.appoinment.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoinment.clinic.entity.Login;

public interface LoginDao extends JpaRepository<Login, String> {

}

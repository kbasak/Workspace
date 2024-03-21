package com.hcm.login.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcm.login.entity.Physician;

public interface PhysicianDao extends JpaRepository<Physician, Long> {

	List<Physician> findByPhysicianname(String physicianname);
}

package com.appoinment.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoinment.clinic.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Integer>{
	public List<Patient> findByUsername(String username);
}

package com.appoinment.clinic.service;

import java.util.List;

import com.appoinment.clinic.entity.Patient;
import com.appoinment.clinic.exceptions.UserAlreadyExistException;

public interface RegService {
	public Patient patientReg(Patient patient)throws UserAlreadyExistException;
	
	public List<Patient> findUserByName(String userName);
}

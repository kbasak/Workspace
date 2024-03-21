package com.appoinment.clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoinment.clinic.dao.LoginDao;
import com.appoinment.clinic.dao.PatientDao;
import com.appoinment.clinic.entity.Login;
import com.appoinment.clinic.entity.Patient;
import com.appoinment.clinic.exceptions.UserAlreadyExistException;

@Service
public class RegServiceImpl implements RegService {

	@Autowired
	public PatientDao patientDao;
	@Autowired
	public LoginDao loginDao;

	@Override
	public Patient patientReg(Patient patient)  throws UserAlreadyExistException {
		if (loginDao.existsById(patient.getUsername())) {

			throw new UserAlreadyExistException(
					"Please Select Different UserName, UserName " + patient.getUsername() + " Already Exist");

		} else {

			Login login = new Login(patient.getUsername(), patient.getPassword());
			loginDao.save(login);
			return patientDao.save(patient);
		}
	}

	@Override
	public List<Patient> findUserByName(String userName) {
		return patientDao.findByUsername(userName);
	}

}

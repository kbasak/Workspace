package com.appoinment.clinic.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appoinment.clinic.dto.PatientDto;
import com.appoinment.clinic.entity.Patient;
import com.appoinment.clinic.exceptions.UserAlreadyExistException;
import com.appoinment.clinic.service.RegService;

@RestController
@CrossOrigin("*")
@RequestMapping("/patient")
public class Registration {

	@Autowired
	public RegService regService;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@PostMapping("/register")
	public ResponseEntity<Object> patientRegister(@Valid @RequestBody PatientDto patientDto)
			throws UserAlreadyExistException {
		
		String pwd = passwordEncoder.encode(patientDto.getPassword());
		patientDto.setPassword(pwd);
		
		patientDto.getGender().toUpperCase();

		Patient patientRequest = modelMapper.map(patientDto, Patient.class);

		Patient patientPost = this.regService.patientReg(patientRequest);

		PatientDto patientResponse = modelMapper.map(patientPost, PatientDto.class);

		return new ResponseEntity<Object>(patientResponse, HttpStatus.ACCEPTED);
	}
}

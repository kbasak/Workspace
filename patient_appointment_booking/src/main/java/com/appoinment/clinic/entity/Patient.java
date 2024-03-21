package com.appoinment.clinic.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Patient_Details")
public class Patient {
	@Id
	@GeneratedValue
	private int patientId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	private String email;
	private String phoneNumber;
}

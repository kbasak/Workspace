package com.appoinment.clinic.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

	public enum Gender {
		   MALE,
		   FEMALE;
		}
	
	@NotBlank(message = "UserName Shouldn't be blank")
	private String username;

	@Pattern(regexp = "^(?=.*\\d).{4,8}$", message="Invalid Password")
	private String password;

	@NotBlank(message = "UserName Shouldn't be blank")
	private String firstName;

	@NotBlank(message = "UserName Shouldn't be blank")
	private String lastName;

	@Pattern(regexp="^(MALE|FEMALE|Male|Female|male|female)$", message="Invalid Gender")
	private String gender;

	@NotNull(message = "DOB shouldn't be null")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	@Email(message = "Invalid Email")
	private String email;

	@Pattern(regexp = "^\\d{10}$", message = "Invalid Mobile Number")
	private String phoneNumber;
}

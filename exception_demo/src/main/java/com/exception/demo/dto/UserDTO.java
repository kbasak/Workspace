package com.exception.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	@NotNull(message = "Name shouldn't be null")
	private String name;

	@Min(18)
	@Max(60)
	private int age;

	@Email(message = "Invalid Email")
	private String email;

	@Pattern(regexp = "^\\d{10}$", message = "Invalid Mobile Number")
	private String phone;

	@NotBlank(message = "Nationality shouldn't be blank")
	private String nationality;
}

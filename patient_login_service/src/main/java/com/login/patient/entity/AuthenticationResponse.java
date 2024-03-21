package com.login.patient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

	private String username;

	private String password;

	private String jwtAuthToken;

	private long serverCurrentTime;

	private long tokenExpirationTime;

}
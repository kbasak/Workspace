package com.login.patient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.patient.dao.LoginDao;
import com.login.patient.dto.LoginDto;
import com.login.patient.dto.VaildatingDTO;
import com.login.patient.entity.AuthenticationResponse;
import com.login.patient.entity.Login;
import com.login.patient.service.LoginServiceImpl;
import com.login.patient.util.JwtUtil;

@RestController
@RequestMapping("/patient")
public class LoginController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private VaildatingDTO vaildatingDTO = new VaildatingDTO();

	@PostMapping("/login")
	public ResponseEntity<Object> createAuthorizationToken(@RequestBody LoginDto loginDto) {

		Login login = new Login();
		login.setUsername(loginDto.getUsername());
		login.setPassword(loginDto.getPassword());

		logger.info("BEGIN - [login(customerLoginCredentials)]");
		final UserDetails userDetails = loginServiceImpl.loadUserByUsername(login.getUsername());
		logger.debug("{}", userDetails);
		Login r = loginDao.findById(login.getUsername()).get();
		String username = r.getUsername();
		String password = r.getPassword();

		// if (userDetails.getPassword().equals(users.getPassword())) {
		if (passwordEncoder.matches(login.getPassword(), userDetails.getPassword())) {
			logger.info("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<Object>(new AuthenticationResponse(username, password,
					jwtUtil.generateToken(userDetails), jwtUtil.getCurrentTime(), jwtUtil.getExpirationTime()),
					HttpStatus.OK);
		}

		logger.info("END - [login(customerLoginCredentials)]");
		return new ResponseEntity<Object>(new AuthenticationResponse(username, password,
				jwtUtil.generateToken(userDetails), jwtUtil.getCurrentTime(), jwtUtil.getExpirationTime()),
				HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VaildatingDTO> validatingAuthToken(@RequestHeader(name = "Authorization") String tokenDup) {
		logger.info("Request is here");
		logger.info("BEGIN - [validatingAuthorizationToken(JWT-token)]");
		String token = tokenDup.substring(7);
		try {
			UserDetails user = loginServiceImpl.loadUserByUsername(jwtUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtUtil.validateToken(token, user))) {
				logger.debug("Token matched is Valid");
				logger.info("Token matched is Valid");
				logger.info("END - validate()");
				vaildatingDTO.setValidStatus(true);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.OK);
			} else {
				throw new Exception("Invalid token");
			}
		} catch (Exception e) {
			logger.debug("Invalid token - Bad Credentials Exception");
			logger.info("END Exception - validatingAuthorizationToken()");
			vaildatingDTO.setValidStatus(false);
			return new ResponseEntity<>(vaildatingDTO, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {

		logger.info("Authorization Microservice is Up and Running....");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}

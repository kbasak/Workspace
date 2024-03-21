package com.hcm.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.login.dao.UserDao;
import com.hcm.login.dto.UserDTO;
import com.hcm.login.dto.VaildatingDTO;
import com.hcm.login.entity.Users;
import com.hcm.login.entity.AuthenticationResponse;
import com.hcm.login.service.UserServiceImpl;
import com.hcm.login.util.JwtUtil;

@RestController
@CrossOrigin("*")
public class AuthorizationController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private UserDao userDao;

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
	private VaildatingDTO vaildatingDTO = new VaildatingDTO();

	@PostMapping("/login")
	public ResponseEntity<Object> createAuthorizationToken(@RequestBody UserDTO userDTO) {

		Users users = new Users();
		users.setUsername(userDTO.getUsername());
		users.setPassword(userDTO.getPassword());
		users.setUserrole(userDTO.getUserrole());

		logger.info("BEGIN - [login(customerLoginCredentials)]");
		final UserDetails userDetails = userServiceImpl.loadUserByUsername(users.getUsername());
		logger.debug("{}", userDetails);
		Users r = userDao.findById(users.getUsername()).get();
		String username = r.getUsername();
		String password = r.getPassword();
		String userrole = r.getUserrole();
		
		//if (userDetails.getPassword().equals(users.getPassword())) {
		if (passwordEncoder.matches(users.getPassword(), userDetails.getPassword())) {
			logger.info("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<>(
					new AuthenticationResponse(username, password, userrole, jwtTokenUtil.generateToken(userDetails),
							jwtTokenUtil.getCurrentTime(), jwtTokenUtil.getExpirationTime()),
					HttpStatus.OK);
		}

		logger.info("END - [login(customerLoginCredentials)]");
		return new ResponseEntity<>(
				new AuthenticationResponse(username, password, userrole, jwtTokenUtil.generateToken(userDetails),
						jwtTokenUtil.getCurrentTime(), jwtTokenUtil.getExpirationTime()),
				HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VaildatingDTO> validatingAuthorizationToken(
			@RequestHeader(name = "Authorization") String tokenDup) {
		logger.info("Request is here");
		logger.info("BEGIN - [validatingAuthorizationToken(JWT-token)]");
		String token = tokenDup.substring(7);
		try {
			UserDetails user = userServiceImpl.loadUserByUsername(jwtTokenUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
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

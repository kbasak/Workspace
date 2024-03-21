package com.hcm.claim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.claim.clients.AuthFeign;
import com.hcm.claim.dto.VaildatingDTO;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthFeign authFeign;

	@Override
	public boolean validateToken(String jwt) {
		VaildatingDTO vaildatingDTO = authFeign.validatingAuthorizationToken(jwt).getBody();
		return vaildatingDTO.isValidStatus();
	}

}

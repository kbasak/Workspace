package com.hcm.claim.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.hcm.claim.dto.VaildatingDTO;



@FeignClient(name="Authorization-ms", url="http://localhost:8081")
public interface AuthFeign {
	@GetMapping("/validate")
	public ResponseEntity<VaildatingDTO> validatingAuthorizationToken(@RequestHeader("Authorization") String tokenDup);
}

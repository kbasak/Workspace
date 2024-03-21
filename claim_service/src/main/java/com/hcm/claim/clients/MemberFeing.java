package com.hcm.claim.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="login-service", url="${http://localhost:8081}")
public interface MemberFeing {

	@GetMapping("/member/uname/{userName}")
	public Long getByUsername(@PathVariable String userName);
	
}

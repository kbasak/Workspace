package com.hcm.claim.service;

import java.util.List;
import java.util.Optional;

import com.hcm.claim.entity.ClaimInfo;
import com.hcm.claim.entity.SearchInfo;

public interface ClaimService {
	public ClaimInfo submitClaim(ClaimInfo claimInfo);

	Optional<ClaimInfo> findByClaimid(long claimid);
	
	public List<ClaimInfo> fetchClaims();
	
    public List<SearchInfo> searchClaims();
}

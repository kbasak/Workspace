package com.hcm.claim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.claim.dao.ClaimDao;
import com.hcm.claim.dao.SearchDao;
import com.hcm.claim.entity.ClaimInfo;
import com.hcm.claim.entity.SearchInfo;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimDao claimDao;

	@Autowired
	private SearchDao searchDao;

	@Override
	public ClaimInfo submitClaim(ClaimInfo claimInfo) {
		return claimDao.save(claimInfo);
	}

	@Override
	public Optional<ClaimInfo> findByClaimid(long claimid) {
		return claimDao.findById(claimid);
	}

	@Override
	public List<ClaimInfo> fetchClaims() {
		return claimDao.findAll();
	}

	@Override
	public List<SearchInfo> searchClaims() {
		return searchDao.findAll();
	}

}

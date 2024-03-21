package com.hcm.claim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.claim.dao.MemberDao;
import com.hcm.claim.dto.ClaimDTO;
import com.hcm.claim.entity.ClaimInfo;
import com.hcm.claim.exception.InvalidToken;
import com.hcm.claim.exception.MemberExist;
import com.hcm.claim.exception.UserNotFound;
import com.hcm.claim.service.AuthService;
import com.hcm.claim.service.ClaimService;

@RestController
@CrossOrigin(origins = "*")
public class ClaimController {

	@Autowired
	private ClaimService claimService;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private AuthService authService;
	Logger logger = LoggerFactory.getLogger(ClaimController.class);

	@PostMapping("/claim")
	public ClaimInfo submitClaim(@RequestBody ClaimDTO claimDTO,
			@RequestHeader(name = "Authorization") String tokenDup) throws UserNotFound, MemberExist, InvalidToken {

		ClaimInfo claim = new ClaimInfo();
		claim.setMemberid(claimDTO.getMemberid());
		claim.setClaimtype(claimDTO.getClaimtype());
		claim.setClaimamount(claimDTO.getClaimamount());
		claim.setClaimdate(claimDTO.getClaimdate());
		claim.setRemarks(claimDTO.getRemarks());

		if (tokenDup.length() > 0 && authService.validateToken(tokenDup)) {
			long memid = claimDTO.getMemberid();
			if (this.memberDao.findById(memid).isPresent()) {
				if (this.memberDao.findById(memid).get().getMemberid() != memid) {
					logger.info("Member ID not exist, Register first");
					return null;
				} else if (claimDTO.getRemarks().equals("") || claimDTO.getClaimamount() == 0
						|| claimDTO.getClaimtype().equals("")) {
					throw new UserNotFound("All field should be filled");
				} else {
					this.claimService.submitClaim(claim);
					logger.info("Claim submitted sucessfully");
					return claim;
				}
			} else {
				throw new MemberExist("Member is not Registered");
			}
		} else {
			throw new InvalidToken("Invalid Token");
		}

	}
}
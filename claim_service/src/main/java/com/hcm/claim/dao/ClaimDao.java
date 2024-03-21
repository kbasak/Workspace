package com.hcm.claim.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcm.claim.entity.ClaimInfo;

public interface ClaimDao extends JpaRepository<ClaimInfo, Long> {

}

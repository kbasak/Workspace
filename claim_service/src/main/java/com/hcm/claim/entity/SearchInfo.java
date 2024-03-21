package com.hcm.claim.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "search")
public class SearchInfo {

	
	private long memberid;
	private String fname;
	private String lname;
	private long physicianid;
	@Id
	private long claimid;
	private double claimamount;
	private Date claimdate;

	public long getMemberid() {
		return memberid;
	}

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public long getPhysicianid() {
		return physicianid;
	}

	public void setPhysicianid(long physicianid) {
		this.physicianid = physicianid;
	}

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	public double getClaimamount() {
		return claimamount;
	}

	public void setClaimamount(double claimamount) {
		this.claimamount = claimamount;
	}

	public Date getClaimdate() {
		return claimdate;
	}

	public void setClaimdate(Date claimdate) {
		this.claimdate = claimdate;
	}

}

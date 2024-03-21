package com.hcm.claim.dto;

import java.util.Date;

public class ClaimDTO {
	private long claimid;
	private int memberid;
	private String claimtype;
	private double claimamount;
	private Date claimdate;
	private String remarks;

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public String getClaimtype() {
		return claimtype;
	}

	public void setClaimtype(String claimtype) {
		this.claimtype = claimtype;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ClaimDTO(long claimid, int memberid, String claimtype, double claimamount, Date claimdate, String remarks) {
		super();
		this.claimid = claimid;
		this.memberid = memberid;
		this.claimtype = claimtype;
		this.claimamount = claimamount;
		this.claimdate = claimdate;
		this.remarks = remarks;
	}

	public ClaimDTO() {}

}

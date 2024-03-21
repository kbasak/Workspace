package com.hcm.claim.entity;

import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="member_id")
	private long memberid;
	@Column
	private String username;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private Date dob;
	@Column
	private String address;
	@Column
	private String state;
	
	@Column
	private String email;
	@Column
	private int physicianid;

	public int getPhysicianid() {
		return physicianid;
	}
	
	public void setPhysicianid(int physicianid) {
		Random r = new Random();
		this.physicianid = (r.nextInt(8));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMemberid() {
		return memberid;
	}

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}

	public Member(String username, String fname, String lname, Date dob, String address, String state, 
			String email) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.address = address;
		this.state = state;
		this.email = email;
		Random r=new Random();
		this.physicianid = r.nextInt(8);
	}

	public Member() {
	}

}
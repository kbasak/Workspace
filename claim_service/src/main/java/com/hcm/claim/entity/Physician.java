package com.hcm.claim.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "physician")
public class Physician {

	@Id
	private long physicianid;
	private String physicianname;
	private String physicianstate;

	public long getPhysicianid() {
		return physicianid;
	}

	public void setPhysicianid(long physicianid) {
		this.physicianid = physicianid;
	}

	public String getPhysicianname() {
		return physicianname;
	}

	public void setPhysicianname(String physicianname) {
		this.physicianname = physicianname;
	}

	public String getPhysicianstate() {
		return physicianstate;
	}

	public void setPhysicianstate(String physicianstate) {
		this.physicianstate = physicianstate;
	}

	public Physician(int physicianid, String physicianname, String physicianstate) {
		super();
		this.physicianid = physicianid;
		this.physicianname = physicianname;
		this.physicianstate = physicianstate;
	}

	public Physician() {
		super();
	}
}

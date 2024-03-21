package com.clinic.services.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ClinicService")
public class ClinicServices {

	private int serialno;
	private String username;
	private String clinic;
	private Date dateofvisit;
	private Time timeofvisit;
	private String service;
}
